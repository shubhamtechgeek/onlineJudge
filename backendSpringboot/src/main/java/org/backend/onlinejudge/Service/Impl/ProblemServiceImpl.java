package org.backend.onlinejudge.Service.Impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.backend.onlinejudge.DTO.PageDTO;
import org.backend.onlinejudge.DTO.ProblemDTO;
import org.backend.onlinejudge.Entity.*;
import org.backend.onlinejudge.Enums.Difficulty;
import org.backend.onlinejudge.Enums.ErrorCode;
import org.backend.onlinejudge.Exception.AppException;
import org.backend.onlinejudge.Query.ProblemQuery;
import org.backend.onlinejudge.Repository.ProblemRepository;
import org.backend.onlinejudge.Repository.UserRepository;
import org.backend.onlinejudge.Service.ProblemService;
import org.backend.onlinejudge.Service.SequenceGeneratorService;
import org.backend.onlinejudge.Util.CommonUtil;
import org.backend.onlinejudge.Util.Converter;
import jakarta.persistence.criteria.Predicate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
//@Slf4j
@Transactional
public class ProblemServiceImpl implements ProblemService {

//    private final SubmissionRepository submissionRepository;
//    private final ProblemMapper problemMapper;

//    private final TagRepository tagRepository;
//    private final TagMapper tagMapper;
    private final ProblemRepository problemRepository;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final SequenceGeneratorService sgs;
    private final MongoTemplate mongoTemplate;

    @PostConstruct
    private void init() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    @Override
    public PageDTO<ProblemDTO> findProblems(ProblemQuery problemQuery, Integer page, Integer size)
            throws JsonProcessingException {
        User user = UserContext.getCurrentUser();
        Pageable pageable = PageRequest.of(page, size);

        Query mongoQuery = createMongoQuery(problemQuery, user);

        List<Problem> problemList = mongoTemplate.find(mongoQuery.with(pageable), Problem.class);
        long count = mongoTemplate.count(mongoQuery, Problem.class);

        List<ProblemDTO> problemDTOs = Converter.problemListToProblemDTOList(problemList);
        if (user != null) {
            for (ProblemDTO dto : problemDTOs) {
                addProblemStatus(dto, user);
            }
        }

        return new PageDTO<>(page, size, count, problemDTOs);
    }

    private Query createMongoQuery(ProblemQuery problemQuery, User user) {
        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();

        String problemCode = problemQuery.getProblemCode();
        if (!CommonUtil.isNull(problemCode)) {
            criteriaList.add(Criteria.where("problemCode").regex(".*" + problemCode + ".*"));
        }

        String title = problemQuery.getTitle();
        String keyword = problemQuery.getKeyword();

        if (!CommonUtil.isNull(title)) {
            criteriaList.add(Criteria.where("problemTitle").regex(".*" + title + ".*", "i"));
        }

        if (!CommonUtil.isNull(keyword)) {
            criteriaList.add(new Criteria().orOperator(
                    Criteria.where("problemTitle").regex(".*" + keyword + ".*", "i"),
                    Criteria.where("problemCode").regex(".*" + keyword + ".*", "i")
            ));
        }

        Difficulty difficulty = problemQuery.getDifficulty();
        if (difficulty != null) {
            criteriaList.add(Criteria.where("difficulty").is(difficulty));
        }

        if (user == null) {
            criteriaList.add(Criteria.where("visible").is(true));
        }

        if (!criteriaList.isEmpty()) {
            criteria.andOperator(criteriaList.toArray(new Criteria[0]));
        }

        return new Query(criteria);
    }


    @Override
    public ProblemDTO create(ProblemDTO problemDTO) throws JsonProcessingException {

        User user = userRepository.findById(problemDTO.getAuthorId()).get();


        LocalDateTime cdDTO = LocalDateTime.now();
        LocalDateTime mdDTO = LocalDateTime.now();

            Optional<Problem> problemByCode = problemRepository.findByProblemCode(
                    problemDTO.getProblemCode());
            if (problemByCode.isPresent()) {
                throw new AppException(ErrorCode.PROBLEM_EXISTS_CODE_ALREADY);
            }
            Optional<Problem> problemByTitle = problemRepository.findByProblemTitle(problemDTO.getProblemTitle());
            if (problemByTitle.isPresent()) {
                throw new AppException(ErrorCode.PROBLEM_EXISTS_TITLE_ALREADY);
            }
            Problem problem = Converter.problemDTOTOproblem(problemDTO);
            problem.setProblemId("P" + sgs.generateSequence(Problem.getSequenceName()));
            problem.setAuthor(user);
            problem.setCreateDate(cdDTO);
            problem.setModifiedDate(mdDTO);
            problem.setVisible(true);
//    problem.setTagList(new HashSet<>());
//    Set<Tag> tagSet = new HashSet<>(
//            processTagSet(problem, tagMapper.toTags(problemDTO.getTagList())));
//    problem.setTagList(tagSet);
//    if (!validateSampleIO(problemDTO.getSampleIOList())) {
//        throw new AppException(ErrorCode.SAMPLE_IO_INVALID);
//    }
            problem.setSampleIO(objectMapper.writeValueAsString(problemDTO.getSampleIOList()));
//    problem.setTestCaseScore(Integer.valueOf(objectMapper.writeValueAsString(problemDTO.getTestCaseInfo())));
            int totalScore = 0;
//    if (Objects.equals(problemDTO.getRuleType(), ContestRuleType.OI.name())) {
//        for (TestCaseInfoDTO info : problemDTO.getTestCaseInfo()) {
//            totalScore += info.getScore();
//        }
//    }
            problem.setTestCaseScore(totalScore);

            problem = problemRepository.save(problem);
            return Converter.problemToProblemDTO(problem);
}


    @Override
    public ProblemDTO findProblemById(String id) throws JsonProcessingException {
        User user = UserContext.getCurrentUser();
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROBLEM_NOTFOUND));
        List<SampleIO> sampleIOList = objectMapper.readValue(problem.getSampleIO(),
                new TypeReference<List<SampleIO>>() {
                });

        ProblemDTO dto = Converter.problemToProblemDTO(problem);
        dto.setSampleIOList(sampleIOList);
        //TO-DO
//        if (user != null) {
//            addProblemStatus(dto, user);
//        }
        return dto;
    }

    @Override
    public ProblemDTO update(ProblemDTO problemDTO) throws JsonProcessingException {
        User user = userRepository.findById(problemDTO.getAuthorId()).get();
//        Long id = problemDTO.getId();
        Problem problem = problemRepository.findById(problemDTO.getProblemId())
                .orElseThrow(() -> new AppException(ErrorCode.PROBLEM_NOTFOUND));
        if (!CommonUtil.ensureCreatedBy(problem, user)) {
            throw new AppException(ErrorCode.OBJECT_NOT_CREATED_BY_USER);
        }

        if (null != problemDTO.getProblemCode() && !problem.getProblemCode()
                .equals(problemDTO.getProblemCode())) {

                Optional<Problem> p = problemRepository.findByProblemCode(problemDTO.getProblemCode());
                if (p.isPresent() && !p.get().getProblemId().equals(problemDTO.getProblemId())) {
                    throw new AppException(ErrorCode.PROBLEM_EXISTS_CODE_ALREADY);
                }

            problem.setProblemCode(problemDTO.getProblemCode());
        }

        if (null != problemDTO.getVisible()) {
            problem.setVisible(problemDTO.getVisible());
        }

        if (!validateSampleIO(problemDTO.getSampleIOList())) {
            throw new AppException(ErrorCode.SAMPLE_IO_INVALID);
        }
        problem.setSampleIO(objectMapper.writeValueAsString(problemDTO.getSampleIOList()));

        if (null != problemDTO.getProblemTitle() && !problem.getProblemTitle().equals(problemDTO.getProblemTitle())) {
                Optional<Problem> p = problemRepository.findByProblemTitle(problemDTO.getProblemTitle());
                if (p.isPresent() && !p.get().getProblemId().equals(problemDTO.getProblemId())) {
                    throw new AppException(ErrorCode.PROBLEM_EXISTS_TITLE_ALREADY);
                }

            problem.setProblemTitle(problemDTO.getProblemTitle());
        }
        if (null != problemDTO.getDifficulty()) {
            problem.setDifficulty(Difficulty.valueOf(problemDTO.getDifficulty()));
        }

        if (null != problemDTO.getDescription()) {
            problem.setDescription(problemDTO.getDescription());
        }

        if (null != problemDTO.getHint()) {
            problem.setHint(problemDTO.getHint());
        }

        if (null != problemDTO.getInputDescription()) {
            problem.setInputDescription(problemDTO.getInputDescription());
        }

        if (null != problemDTO.getOutputDescription()) {
            problem.setOutputDescription(problemDTO.getOutputDescription());
        }

        if (null != problemDTO.getMemoryLimit()) {
            problem.setMemoryLimit(problemDTO.getMemoryLimit());
        }

        if (null != problemDTO.getTimeLimit()) {
            problem.setTimeLimit(problemDTO.getTimeLimit());
        }

        if (null != problemDTO.getTestCaseId()) {
            problem.setTestCaseId(problemDTO.getTestCaseId());
        }


        if (null != problemDTO.getTestCaseInfo()) {
            problem.setTestCaseScore(Integer.valueOf(objectMapper.writeValueAsString(problemDTO.getTestCaseInfo().toString())));
            int totalScore = 0;
//            if (Objects.equals(problemDTO.getRuleType(), ContestRuleType.OI.name())) {
//                for (TestcaseInfoDTO info : problemDTO.getTestcaseInfos()) {
//                    totalScore += info.getScore();
//                }
//            }
            problem.setTestCaseScore(totalScore);
        }


//        if (null != problemDTO.getTagList()) {
//            Set<Tag> tagSet = new HashSet<>(
//                    processTagSet(problem, tagMapper.toTags(problemDTO.getTagList())));
//            problem.setTagList(tagSet);
//        }
        return Converter.problemToProblemDTO(problemRepository.save(problem));

    }

    private boolean validateSampleIO(List<SampleIO> sampleIOList) {
        for (SampleIO sampleIO : sampleIOList) {
            if (CommonUtil.isNull(sampleIO.getInput()) || CommonUtil.isNull(sampleIO.getOutput())) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public ProblemDTO delete(String id) throws AppException {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROBLEM_NOTFOUND));
        User user = userRepository.findById(problem.getAuthor().getUserId()).get();

        if (!CommonUtil.ensureCreatedBy(problem, user)) {
            throw new AppException(ErrorCode.OBJECT_NOT_CREATED_BY_USER);
        }

//        Set<Tag> tagSet = problem.getTagList();
//        for (Tag tag : tagSet) {
//            tag.setProblemCount(tag.getProblemCount() - 1);
//        }
//        tagRepository.saveAll(tagSet);
//        submissionRepository.deleteByProblemId(id);
        problemRepository.delete(problem);
        return Converter.problemToProblemDTO(problem);
    }

    //TO-DO

    private void addProblemStatus(ProblemDTO dto, User user) throws JsonProcessingException {
//        String acmProblemsStatusJson = user.getAcmProblemsStatus();
//        String oiProblemsStatusJson = user.getOiProblemsStatus();
//        if (dto.getRuleType().equals(ContestRuleType.ACM.name())) {
//            AcmProblemStatus acmProblemStatus = objectMapper.readValue(acmProblemsStatusJson,
//                    AcmProblemStatus.class);
//            if (acmProblemStatus.getProblems() == null
//                    || acmProblemStatus.getProblems().get(dto.getId()) == null) {
//                dto.setMyStatus(null);
                return;
//            }
//            dto.setMyStatus(acmProblemStatus.getProblems().get(dto.getId()).getStatus());
//        } else {
//            OiProblemStatus oiProblemStatus = objectMapper.readValue(oiProblemsStatusJson,
//                    OiProblemStatus.class);
//            if (oiProblemStatus.getProblems() == null
//                    || oiProblemStatus.getProblems().get(dto.getId()) == null) {
//                dto.setMyStatus(null);
//                return;
//            }
//            dto.setMyStatus(oiProblemStatus.getProblems().get(dto.getId()).getStatus());
//        }
//    }
    }
}

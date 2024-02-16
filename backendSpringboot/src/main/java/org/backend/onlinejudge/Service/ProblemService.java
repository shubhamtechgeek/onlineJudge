package org.backend.onlinejudge.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.backend.onlinejudge.DTO.PageDTO;
import org.backend.onlinejudge.DTO.ProblemDTO;
import org.backend.onlinejudge.Exception.AppException;
import org.backend.onlinejudge.Query.ProblemQuery;

public interface ProblemService {

    ProblemDTO findProblemById(String id) throws JsonProcessingException;
    PageDTO<ProblemDTO> findProblems(ProblemQuery problemQuery , Integer page, Integer size)
            throws JsonProcessingException;
    ProblemDTO create(ProblemDTO problemDTO) throws JsonProcessingException ;
    ProblemDTO update(ProblemDTO problemDTO) throws JsonProcessingException;
    ProblemDTO delete(String id) throws AppException;
//    PageDTO<ProblemDTO> adminFindProblems(ProblemQuery problemQuery, Integer page, Integer size);
//    ProblemDTO adminFindProblemById(Long id) throws JsonProcessingException;
}

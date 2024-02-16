package org.backend.onlinejudge.Enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("000" , "Internal server error" , HttpStatus.INTERNAL_SERVER_ERROR),
    PROBLEM_NOTFOUND("001","Problem not found" , HttpStatus.NOT_FOUND),
    PROBLEM_EXISTS_CODE_ALREADY( "002" , "Problem code exists already" , HttpStatus.BAD_REQUEST),
    PROBLEM_EXISTS_TITLE_ALREADY("003" , "Problem title exists already" , HttpStatus.BAD_REQUEST ),
    SAMPLE_IO_INVALID("004", "Sample input/output is not valid", HttpStatus.BAD_REQUEST ),
    TEST_DATA_PATH_INVALID( "005", "Test data path is invalid"  , HttpStatus.BAD_REQUEST),
    EMPTY_FILE("006", "File upload is empty" , HttpStatus.BAD_REQUEST ),
    EMPTY_ZIP_FILE("007", "Empty Zip file", HttpStatus.BAD_REQUEST),
    JUDGE_POST_ERROR("009" , "Failed to send code to judger module. Please check again" , HttpStatus.INTERNAL_SERVER_ERROR),
    NO_SUCH_SUBMISSION("010" , "Submission not found" , HttpStatus.NOT_FOUND),
    NO_SUCH_USER("011" , "User not found" , HttpStatus.NOT_FOUND ) ,
    START_TIME_IS_EARLY_THAN_NOW("013", "Start time is early than now", HttpStatus.BAD_REQUEST),
    START_TIME_IS_AFTER_THAN_END_TIME("014", "Start time is after than end time" , HttpStatus.BAD_REQUEST),
    DUPLICATED_USERNAME("018", "User with username is exists", HttpStatus.BAD_REQUEST),
    DUPLICATED_EMAIL("019", "User with email is exists" , HttpStatus.BAD_REQUEST ),
    USER_DISABLED( "020", "User is disabled" , HttpStatus.BAD_REQUEST),
    BAD_CREDENTIALS("021", "Bad credentials" , HttpStatus.BAD_REQUEST),
    NOT_A_ZIP_FILE("022", "Not a zip file",HttpStatus.BAD_REQUEST),
    INVALID_TEST_DATA_FORMAT("023", "Test data input/output wrong format" ,HttpStatus.BAD_REQUEST),
    NO_SUCH_PROBLEM("024", "No such problem", HttpStatus.BAD_REQUEST ),
    BAD_PASSWORD( "035", "Wrong password" , HttpStatus.BAD_REQUEST),
    PROCESS_TESTCASE_ERROR("037", "Process test case error" , HttpStatus.BAD_REQUEST),
    WRONG_PROBLEM_RULE_TYPE("038" , "Wrong problem rule type" , HttpStatus.BAD_REQUEST),
    OBJECT_NOT_CREATED_BY_USER( "040","Object not created by user " ,HttpStatus.BAD_REQUEST ),
    TEST_CASE_NOT_FOUND("042", "Test case not found " , HttpStatus.BAD_REQUEST),
    EXPORT_ERROR("044", "Export error ", HttpStatus.BAD_REQUEST),
    NO_SUCH_ANNOUNCEMENT("045", "No such announcement",HttpStatus.BAD_REQUEST),
    HAVE_SUCH_ANNOUNCEMENT("046", "Announcement already exists", HttpStatus.BAD_REQUEST),
    ISSUE_NOTFOUND("047", "Issue not found", HttpStatus.NOT_FOUND),
    TOKEN_NOT_FOUND("048", "Token not found",HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_CONFIRMED("049", "Email already confirmed " , HttpStatus.NOT_FOUND),
    TOKEN_EXPIRED("050", "Token expired " ,HttpStatus.NOT_FOUND ),
    EMPTY_TEST_CASE("051","Empty testcase" , HttpStatus.BAD_REQUEST),
    WRONG_TESTCASE_FORMAT( "052", "Wrong testcase format" ,HttpStatus.BAD_REQUEST ),
    TOKEN_ALREADY_USED("053", "Token already used" , HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCH( "054", "Password do not match" , HttpStatus.BAD_REQUEST);



    private final String code;
    private final String message;
    private final HttpStatus status;
    ErrorCode(String code ,String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}

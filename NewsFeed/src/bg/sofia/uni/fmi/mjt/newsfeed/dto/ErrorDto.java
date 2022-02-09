package bg.sofia.uni.fmi.mjt.newsfeed.dto;

public class ErrorDto implements ResponseDto {
    public String status;
    public String code;
    public String message;

    @Override
    public String show() {
        return "Error: " +
                "status= " + status +
                "\ncode= " + code +
                "\nmessage= " + message + '\n';
    }
}

package util;

/**
 * @author  zhangys
 * Desc：业务异常
 */
public class AppException extends RuntimeException {
   private static final String EX_MSG = "自定义业务系统异常。";
   public AppException() {
      this(EX_MSG);
   }

   public AppException(String message) {
      super(message);
   }

   public AppException(Throwable cause) {
      super(EX_MSG,cause);
   }
   public AppException(String message, Throwable cause) {
      super(message, cause);
   }
}

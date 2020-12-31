package util;

import java.io.Serializable;
import java.util.List;

/**
 * @Author zhangys
 * Desc：执行SQL返回结果的封装
 */
public class CapsRes implements Serializable {
   private List<String> flds;
   private List<List<String>> rs;
   // 1:是查询结果 0:其他
   private Integer type;
   //总数量
   private Integer ttlNum;

   public List<String> getFlds() {
      return flds;
   }

   public void setFlds(List<String> flds) {
      this.flds = flds;
   }

   public List<List<String>> getRs() {
      return rs;
   }

   public void setRs(List<List<String>> rs) {
      this.rs = rs;
   }

   public Integer getType() {
      return type;
   }

   public void setType(Integer type) {
      this.type = type;
   }

   public Integer getTtlNum() {
      return ttlNum;
   }

   public void setTtlNum(Integer ttlNum) {
      this.ttlNum = ttlNum;
   }

   @Override
   public String toString() {
      StringBuilder strBuf = new StringBuilder();
      strBuf.append("type=").append(this.type).append(";ttlNum=").append(this.ttlNum)
              .append(";flds=").append(null != flds ? this.flds.toString() : "null")
              .append(";rs=").append(null != rs ? this.rs.toString() : "null");
      return strBuf.toString();
   }
}

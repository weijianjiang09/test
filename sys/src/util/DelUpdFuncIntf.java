package util;

import java.util.Map;

/**
 * @Author zhangys
 * Desc：删除和修改按钮回调的函数式接口
 */
@FunctionalInterface
public interface DelUpdFuncIntf {
   void doDelUpd(Map<String,String> recMap,String idCol);
}

package cn.edu.bupt.opensource.hrm.common.util.tag;

import cn.edu.bupt.opensource.hrm.common.util.contant.HrmConstants;

/**
 * <p>Title: PageModel</p>
 * <p>Description: 分页实体</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-07 00:45</p>
 * @author ChengTengfei
 * @version 1.0
 */
public class PageModel {

    // 总记录数
    private int recordCount;
    // 当前分页数
    private int pageIndex ;
    // 分页大小
    private int pageSize = HrmConstants.PAGE_DEFAULT_SIZE = 4;
    // 总分页数
    private int totalSize;

    public int getRecordCount() {
        this.recordCount = this.recordCount <= 0 ? 0:this.recordCount;
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageIndex() {
        this.pageIndex = this.pageIndex <= 0?1:this.pageIndex;
        /** 判断当前页面是否超过了总页数:如果超过了默认给最后一页作为当前页  */
        this.pageIndex = this.pageIndex>=this.getTotalSize()?this.getTotalSize():this.pageIndex;

        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        this.pageSize = this.pageSize <= HrmConstants.PAGE_DEFAULT_SIZE?HrmConstants.PAGE_DEFAULT_SIZE:this.pageSize;
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        if(this.getRecordCount() <=0){
            totalSize = 0 ;
        }else{
            totalSize = (this.getRecordCount() -1)/this.getPageSize() + 1;
        }
        return totalSize;
    }

    public int getFirstLimitParam(){
        return (this.getPageIndex()-1)*this.getPageSize() ;
    }

}


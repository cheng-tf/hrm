package cn.edu.bupt.opensource.hrm.web.listener;

import cn.edu.bupt.opensource.hrm.domain.pojo.PersonModel;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Excel解析监听器
 * @author chengtf
 * @date 2019/1/20
 */
public class ExcelProcessingListener extends AnalysisEventListener<PersonModel> {

    private static Logger log = LoggerFactory.getLogger(ExcelProcessingListener.class);

    private static final Executor executor = Executors.newSingleThreadExecutor();

    private List<PersonModel> persons = Lists.newArrayList();

    /**
     * 读取一行数据时的工作
     * @param personModel
     * @param analysisContext
     */
    @Override
    public void invoke(PersonModel personModel, AnalysisContext analysisContext) {
        log.info(personModel.toString());
        PersonModel person = new PersonModel();
        persons.add(personModel);
    }

    /**
     * 读取所有数据后的工作
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if(CollectionUtils.isEmpty(persons)) {
            log.info("文件的数据为空！");
        }
    }
}

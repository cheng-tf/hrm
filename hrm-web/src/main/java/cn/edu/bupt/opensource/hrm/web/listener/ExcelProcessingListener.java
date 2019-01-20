package cn.edu.bupt.opensource.hrm.web.listener;

import cn.edu.bupt.opensource.hrm.domain.enums.SexEnum;
import cn.edu.bupt.opensource.hrm.domain.pojo.PersonModel;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.*;
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
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("D:\\" +File.separator + "报错文件.txt"), true), "UTF-8"));
            String sex = personModel.getSex();
            if(null == sex) {
                log.warn("性别不能为空！");
                bufferedWriter.write("第" + analysisContext.getCurrentRowNum() + "行数据有误：性别不能为空！" + "\r\n");
                bufferedWriter.close();
                return;
            }
            if(!(SexEnum.BOY.getDescription().equals(sex) || SexEnum.GIRL.getDescription().equals(sex))) {
                log.warn("性别信息有误，请修改！");
                bufferedWriter.write("第" + analysisContext.getCurrentRowNum() + "行数据有误：性别信息有误，请修改！" + "\r\n");
                bufferedWriter.close();
                return;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
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

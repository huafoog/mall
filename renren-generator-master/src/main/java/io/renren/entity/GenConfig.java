package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author qingshan
 */
@TableName("gen_config")
@Data
public class GenConfig extends Model<GenConfig> {
    private String mainPath;
    private String packageName;
    private String moduleName;
    private String author;
    private String email;
    private String tablePrefix;
}

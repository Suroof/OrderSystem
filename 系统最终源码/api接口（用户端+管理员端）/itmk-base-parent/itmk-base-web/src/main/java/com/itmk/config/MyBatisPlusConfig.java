package com.itmk.config;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自由如風: 自由如风
 * @create: 2023-06-28
 */

@Configuration //表明MyBatisPlusconfig是一个配置类
@MapperScan("com.itmk.*.*.mapper") //配置扫描的dao/mapper接口
public class MyBatisPlusConfig {
    // 最新版   新的分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));//支持的数据库类型
        return interceptor;
    }
}

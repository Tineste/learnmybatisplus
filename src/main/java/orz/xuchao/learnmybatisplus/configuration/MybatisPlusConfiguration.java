package orz.xuchao.learnmybatisplus.configuration;


import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//乐观锁插件
@Configuration
public class MybatisPlusConfiguration {
    //乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
//    性能分析插件，在非生产环境下才会生效
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor=new PerformanceInterceptor();
        performanceInterceptor.setFormat(true);
//        设置sql执行时间监控，发现大于设置的会报错
//        performanceInterceptor.setMaxTime(5L);
        return performanceInterceptor;
    }


    //分页插件,可以给所有的增删改查加统一的东西，也可以让个别过滤统一的东西
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor=new PaginationInterceptor();


        return  paginationInterceptor;
    }
}

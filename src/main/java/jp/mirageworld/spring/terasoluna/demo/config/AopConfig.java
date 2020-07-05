package jp.mirageworld.spring.terasoluna.demo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {

	// <!-- AOP. -->
	// <bean id="resultMessagesLoggingInterceptor"
	// class="org.terasoluna.gfw.common.exception.ResultMessagesLoggingInterceptor">
	// <property name="exceptionLogger" ref="exceptionLogger" />
	// </bean>

	// <aop:config>
	// <aop:advisor advice-ref="resultMessagesLoggingInterceptor"
	// pointcut="@within(org.springframework.stereotype.Service)" />
	// </aop:config>

}

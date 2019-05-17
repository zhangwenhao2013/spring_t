
### 通过Aspect注解的方式实现AOP 

###### @Aspect

    @Aspect需要配合@Component之类的类注解才可以被AOP自动扫描;
    @Aspect 用来标注切面,切面里面提供@Pointcut ,Advice ,Around ,Introduction
    @Aspect 注解标注的类,会自动从容器实例中过滤掉;
    
###### @Pointcut

    @Pointcut value 用来指定 express
              argNamse 用来指定参数名称,必须一一对应,只有遇到第一个参数是dingJoinPoint类的参数才可以忽略;
              
```
 @Pointcut(value = "execution(* com.conan.springt.beans.Bizlogic.save(java.lang.String)) && args(ss) "
    ,argNames = "ss")
    public void executePointcut(String ss) {

    }
```              
              
    
###### @Before

    @Before value 用来指定 express
                  argNamse 用来指定参数名称;
    可以获取执行pointcut 方法时传递进来参数;
    1:可以通过参数形式             
    2:可以通过注解形式
    
     注意 && 后面的形式  &&args(xxx)  和  && @annotation(xxx) 
    
```

 /**
     * @param sss 获取方法传入参数,可以指定运行时环境 传入的具体参数
     *            <p>
     *            argNames = "sss" 指定注解方法的参数名 如果有多个 就是 && args(a) && args(b)
     */
    @Before(value = "execution(* com.conan.springt.beans.Bizlogic.save(java.lang.String)) && args(sss))"
            , argNames = "sss") // 可以拿到传入参数的值, argNames = "sss" 指定注解方法的参数名, 参数类型必须和pointcut 中定义的一致;
    public void executeSaveBefore(String sss) {
        System.out.println(" executeSaveBefore1  " + sss);
    }

    @Before(value = "executePointcut(sss)"
            , argNames = "sss") // 可以拿到传入参数的值, argNames = "sss" 指定注解方法的参数名, 参数类型必须和pointcut 中定义的一致;
    public void executeSaveBefore2(String sss) {
        System.out.println(" executeSaveBefore2  " + sss);
    }


    /**
     * @param auditable 通过注解 注解方法,可以手动控制传入advice的参数逻辑
     */
    @Before(value = "execution(* com.conan.springt.beans.Bizlogic.save(java.lang.String)) && @annotation(auditable))"
            , argNames = "auditable") // 可以拿到传入参数的值, argNames = "sss" 指定注解方法的参数名, 参数类型必须和pointcut 中定义的一致;
    public void executeSaveBefore3(Auditable auditable) {
        String value = auditable.value();
        System.out.println(" executeSaveBefore3  " + value);
```   

 ###### @AfterReturning
    
    @AfterReturning value 用来指定 express
                      argNamse 用来指定参数名称;
                      returning 可以获取方法返回的内容;
        
```
    @AfterReturning(value = "execution(* com.conan.springt.beans.Bizlogic.save(java.lang.String)))"
            , returning = "returnValue") //可以拿到返回值
    public void executeSaveAfterReturning(Object returnValue) {

        String str = (String) returnValue;

        System.out.println(" executeSaveAfterReturning " + str);
    }
```


 ###### @AfterThrowing
    
    @AfterReturning value 用来指定 express
                          argNamse 用来指定参数名称;
                          throwing 可以获取抛出的异常;
                                                    
```
 @AfterThrowing(value = "execution(* com.conan.springt.beans.Bizlogic.save(java.lang.String)))"
            , throwing = "ex") //可以拿到抛出的异常
    public void executeSaveAfterThrowing(Exception ex) {
        System.out.println(" executeSaveAfterThrowing " + ex.getStackTrace());
    }
```    


 ###### @Around
    
    @Around value 用来指定 express
            argNamse 用来指定参数名称;
            
             注意 && 后面的形式  &&args(xxx)  和  && @annotation(xxx)  也可以通过这种方式来获取参数
 
```

    @Around(value = "execution(* com.conan.springt.beans.Bizlogic.save(java.lang.String)))")
    public Object executeSaveAround(ProceedingJoinPoint proceedingJoinPoint) {

        Object proceed = null;
        try {
            System.out.println(" executeSaveAround  前面");
            proceed = proceedingJoinPoint.proceed();
            System.out.println(" executeSaveAround  后面");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;

    }
``` 

 ###### Introduction @DeclareParents
 
    @DeclareParents
        value = "xxx.xxx.xxx+" 指定增强xxx.xxx.xxx这个类,接口的作用也是作用在这个类上
        defaultImpl 指定默认要接口的默认实现
        效果是从容器中获取xxx.xxx.xxx的实例,可以直接使用默认接口实现类的方法;
```
@DeclareParents(value = "com.conan.springt.beans.BizLogicImpl+" // xxxx + 表示对 xxxx 这个类的增强;
            , defaultImpl = UsageTrackedImpl.class)                 // 增强的默认实现
    public UsageTracked mixin;
    
    
    通过获取到的BizLogicImpl实例 可以直接强转为UsageTrackedImpl;
    
```  



 ##### Spring 事务管理
 
 ###### 编程式事务管理
 
   1:转账环境的搭建
        
        需要的依赖: 
                   数据库驱动 :mysql driver 
                   jdbc connection
                   C3P0 链接池 mchange
                   
        Spring : 
                   Beans
                   context 
                   core
                   expression
                   loggin
                   tx 
                   
                   logging
                   log4j  
                   aspectj     
                   
                   spring aop
                   aopalliance    
                   
        测试:       spring-test 
                    junit > 4.12   
                    
         配置文件   spring config
                   jdbc.properties
                   
        代码部分:
            1:注意下 Dao 实现类继承JdbcDaoSupport的这种使用方式;
            2:注册Service  DAO,并注入DAO 给Service;
            3:注册S3P0 连接池 并注入数据库的参数信息  驱动,url,username,pwd;
            4:注入S3P0 连接池给继承了JdbcDaoSupport 的DAO实现类;
            5:DAO实现类 getJdbcTemplate 获取模板操作数据库; 
    
    
   2: 编程式事务管理   
        
        目的:提供TransactionManager
        1:注意下 Dao 实现类继承JdbcDaoSupport的这种使用方式;
        2:注册Service  DAO,并注入DAO 给Service;
        3:注册DataSourceTransactionManager(Mysql),并注入S3P0连接池
        4:注册TransactionTemplate,并注入注册DataSourceTransactionManager;
        5:注入注册TransactionTemplate给注册Service;
        6:在Service实现类中,用TransactionTemplate 管理事务
        
 ```
    private TransactionTemplate transactionTemplate;

    public void transfer(final String out, final String in, final Double money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                accountDao.inMoney(in, money);
//                int i = 1 / 0;
                accountDao.outMoney(out, money);
            }
        });

    }
```



  ###### 声明式事务管理
  
  
  1:基于 TransactionProxyFactoryBean 的代理Service方式实现事务管理;
    
    1:注册DataSourceTransactionManager ,并注入S3P0连接池;
    2:注册 基于TransactionProxyFactoryBean增强的Service Bean : target,transactionManager,transactionAttributes;
    3:<props> <prop>格式
    PROPAGATION,ISOLATION,readOnly,-Exception,+Exception
    4:缺点: 每个Service 都需要定义一个增强的 基于TransactionProxyFactoryBean增强的Service
    
  
  2:基于AspectJ  xml 配置声明式的事务管理
  
    1:注册DataSourceTransactionManager ,并注入S3P0连接池;
    2:定义txAdvice,并注入DataSourceTransactionManager,配置传播,隔离信息等
    3:定义pointcut 
    4:定义切面
    
    
    通过基于AspectJ  xml 配置声明式,获取到的Service已经是增强的了;
    配置好,类上无需任何修改;无侵入性;
    
    
  ```
 <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="transfer"
                       propagation="REQUIRED"
            />
        </tx:attributes>
    </tx:advice>

    <aop:config>
        <aop:pointcut id="pointcut1"
                      expression="execution(* com.conan.springtransaction.service.AccountService.transfer(..))"/>
    </aop:config>

    <aop:config>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="pointcut1"/>
    </aop:config>
    
```
    
   3:基于注解方式的声明式事务管理
   
    1:1:注册DataSourceTransactionManager ,并注入S3P0连接池;
    2:<tx:annotation-driven transaction-manager = "transactionManager">
    3:在要用的到事务管理的类中使用注解 @Transactional ;
    
    配置简单,但是需要在每个用到的地方使用注解,有侵入性;
    
   
  
    
  
    
                                    
                   
             
      
    
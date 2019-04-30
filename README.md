
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

      
    
# personnel-management-server
企业人员管理系统后端部分。

# 简介

基于 Spring Boot 、 Spring Boot Jpa、 JWT、Spring Security、Redis、Vue的前后端分离的企业人员管理系统，项目采用分模块开发方式， 权限控制采用 RBAC，支持数据字典与数据权限管理，支持前端菜单动态路由。

# 文件结构

- common 公共模块
  - exception 项目统一异常的处理
  - mapper mapstruct的通用mapper
  - redis redis缓存相关配置
  - utils 系统通用工具类
- system 系统核心模块（系统启动入口）
  - config 配置跨域与静态资源，与数据权限
  - modules 系统相关模块(登录授权、定时任务等)
- logging 系统日志模块
- tools 系统第三方工具模块

![1571798926795](README/1571798926795.png)

# 数据库设计

![1571799115015](README/1571799115015.png)

![1571799292782](README/1571799292782.png)

# 模块

- 用户管理：提供用户的相关配置，新增用户后，默认密码为123456
- 角色管理：对权限与菜单进行分配，可根据部门设置角色的数据权限
- 权限管理：权限细化到接口，可以理解成按钮权限
- 菜单管理：已实现菜单动态路由，后端可配置化，支持多级菜单
- 部门管理：可配置系统组织架构，树形表格展示
- 岗位管理：配置各个部门的职位
- 字典管理：应广大码友的要求加入字典管理，可维护常用一些固定的数据，如：状态，性别等
- 操作日志：记录用户操作的日志
- 异常日志：记录异常日志，方便开发人员定位错误
- 系统缓存：使用jedis将缓存操作可视化，并提供对redis的基本操作
- SQL监控：采用druid 监控数据库访问性能，默认用户名druid，密码druid
- 定时任务：整合Quartz做定时任务，加入任务日志，任务运行情况一目了然
- 邮件工具：发送html格式的邮件
- 免费图床：使用sm.ms图床，用作公共图片上传使用

# 权限控制

本系统权限控制采用 RBAC思想。简单地说，一个用户拥有若干角色，每一个角色拥有若干权限，每一个角色拥有若干个菜单，这样，就构造成“用户-角色-权限”，“角色-菜单” 的授权模型。在这种模型中，用户与角色、角色与权限、角色与菜单之间构成了多对多的关系，如下图：

![1571799087182](README/1571799087182.png)

## 后端权限控制

本系统安全框架使用的是 Spring Security 和 Jwt Token， 访问后端接口需在请求头中携带 token 进行访问，请求头格式如下：

```
# Authorization: Bearer 登录时返回的token
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1ODk2NzY0OSwiaWF0IjoxNTU4OTQ2MDQ5fQ.jsJvqHa1tKbJazG0p9kq5J2tT7zAk5B6N_CspdOAQLWgEICStkMmvLE-qapFTtWnnDUPAjqmsmtPFSWYaH5LtACopy to clipboardErrorCopied
```

也可以过滤一些接口如`Druid`监控等。
配置文件位于：system -> modules -> security ->  config -> SecurityConfig

```java
// 部分略
protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            // 禁用 CSRF
            .csrf().disable()
            // 授权异常
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            // 不创建会话
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers("/druid/**").permitAll()
            // 所有请求都需要认证
            .anyRequest().authenticated();
    httpSecurity
            .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
}Copy to clipboardErrorCopied
permitAll()` 方法指所有登录和未登录人员都可以访问，这个会经过 `security filter`
`anonymous()` 所有人都能访问，但是这个不会经过 `security filter
```

## 系统数据交互

用户登录 -> 后端验证登录返回 `token` -> 前端带上`token`请求后端数据 -> 后端返回数据， 数据交互流程如下： ![img](README/5c9c93b996ace.png)

## 接口权限控制

下面的接口表示用户拥有 `ADMIN`、`MENU_ALL`、`MENU_EDIT` 三个权限中的任意一个就能能访问`update`方法，如果方法不加`@preAuthorize`注解，意味着所有用户都带上有效的`token`后能访问 `update` 方法

```java
@Log(description = "修改菜单")
@PutMapping(value = "/menus")
@PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_EDIT')")
public ResponseEntity update(@Validated @RequestBody Menu resources){
    // 略
}Copy to clipboardErrorCopied
```

# 通用查询

本系统对Jpa的查询进行了封装，现可以通过注解方式实现简单的查询与复杂查询，简单查询：`等于、大于等于、小于等于、模糊查询、包含（IN）查询等`，复杂查询：`左连接、右连接`，如需使用复杂查询，可以查看源码中的 `JobQueryCriteria`，下面介绍简单查询的使用方法

## 使用方式

1、首先编写查询类，如日志查询：

```java
/**
 * 日志查询类
 * @author dormirr
 */
@Data
public class LogQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String username;

    @Query
    private String logType;

    @Query(type = Query.Type.INNER_LIKE)
    private String description;
}Copy to clipboardErrorCopied
```

2、在控制器中使用

```java
public ResponseEntity getLogs(LogQueryCriteria criteria, Pageable pageable){
    return new ResponseEntity(logService.queryAll(criteria,pageable), HttpStatus.OK);
}Copy to clipboardErrorCopied
```

3、Service 中查询

```java
@Override
public Object queryAll(LogQueryCriteria criteria, Pageable pageable){
    Page<Log> page = logRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)),pageable);
    return page;
}Copy to clipboardErrorCopied
```

这样做的好处是，如果需要添加一个字段查询，只需要在查询类中添加就可以了，可以节省大量时间

# 系统缓存

本系统缓存使用的是 `redis`，默认使用 `Spring` 的注解对系统缓存进行操作，并且提供了可视化的 `redis` 缓存操作

## 配置缓存

`redis` 配置文件位于 `eladmin-common - > redis`，部分配置文件如下：

```java
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.password}")
    private String password;

    /**
     * 配置 redis 连接池
     * @return
     */
    @Bean
    public JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        if (StrUtil.isNotBlank(password)) {
            return new JedisPool(jedisPoolConfig, host, port, timeout, password);
        } else {
            return new JedisPool(jedisPoolConfig, host, port,timeout);
        }
    }

    /**
     *  设置 redis 数据默认过期时间
     *  设置@cacheable 序列化方式
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer)).entryTtl(Duration.ofHours(2));
        return configuration;
    }

    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        //序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        // value值的序列化采用fastJsonRedisSerializer
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // 全局开启AutoType，不建议使用
        // ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 建议使用这种方式，小范围指定白名单
        ParserConfig.getGlobalInstance().addAccept("me.zhengjie.system.service.dto");
        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * 自定义缓存key生成策略
     * 使用方法 @Cacheable(keyGenerator="keyGenerator")
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            log.info(sb.toString());
            return sb.toString();
        };
    }
}Copy to clipboardErrorCopied
```

# 异常处理

我们开发项目的时，数据在请求过程中发生错误是非常常见的事情。如：权限不足、数据唯一异常、数据不能为空异常、义务异常等。这些异常如果不经过处理会对前端开发人员和使用者造成不便，因此我们就需要统一处理他们。
源码位于：common - > exception

# 系统日志

本系统使用 `AOP` 记录用户操作日志，只需要在 `controller` 的方法上使用 `@Log("")` 注解，就可以将用户操作记录到数据库，源码可查看 logging
模块具体使用如下：

```java
@Log("新增用户")
@PostMapping(value = "/users")
@PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_CREATE')")
public ResponseEntity create(@Validated @RequestBody User resources){
    checkLevel(resources);
    return new ResponseEntity(userService.create(resources),HttpStatus.CREATED);
}Copy to clipboardErrorCopied
```

页面上可以看到操作日志和异常日志

# 数据权限

本系统是基于部门做的一个简单数据权限控制，也就是通过用户角色中的数据权限控制用户能看哪些数据。

## 角色数据权限

系统提供了三种数据权限控制

- 全部数据权限 无数据权限限制
- 本级数据权限 限制只能看到本部门数据
- 自定义数据权限 可根据实际需要选择部门控制数据权限

# 定时任务

对于简单的定时任务用 `Spring`的 `@Scheduled` 注解即可，如需要动态管理定时任务就需要使用到 `Quartz`。本系统的动态定时任务源码位于 system -> modules -> quartz，使用流程如下

## 编写任务处理类

```java
@Slf4j
@Component
public class TestTask {

    public void run(){ log.info("执行成功");  }

    public void run1(String str){ log.info("执行成功，参数为： {}" + str); }
}Copy to clipboardErrorCopied

```

## 创建定时任务

打开定时任务页面，点击新增按钮创建定时任务，部分参数解释如下：

- Bean名称：Spring Bean名称，如： testTask
- 方法名称：对应后台任务方法名称 方法参数：对应后台任务方法名称值，没有可不填
- cron表达式：可查询官方cron表达式介绍
- 状态：是否启动定时任务

## 常用cron表达式

```
0 0 10,14,16 * * ? 每天上午10点，下午2点，4点 
0 0/30 9-17 * * ? 朝九晚五工作时间内每半小时 
0 0 12 ? * WED 表示每个星期三中午12点 
"0 0 12 * * ?" 每天中午12点触发 
"0 15 10 ? * *" 每天上午10:15触发 
"0 15 10 * * ?" 每天上午10:15触发 
"0 15 10 * * ? *" 每天上午10:15触发 
"0 15 10 * * ? 2005" 2005年的每天上午10:15触发 
"0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发 
"0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发 
"0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发 
"0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发 
"0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发 
"0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发 
"0 15 10 15 * ?" 每月15日上午10:15触发 
"0 15 10 L * ?" 每月最后一日的上午10:15触发 
"0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发 
"0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发 
"0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发Copy to clipboardErrorCopied

```

# 系统工具

这里列举出项目中使用到的工具类

- ElAdminConstant：系统常用常量定义
- EncryptUtils：加密工具，包括对称加密解密，md5加密
- FileUtils：文件工具类
- PageUtils：分页工具类
- RequestHolder：随时获取 HttpServletRequest
- SecurityUtils：获取当前用户
- SpringContextHolder：随时获取bean
- StringUtils：字符串工具类
- ThrowableUtils：异常工具，获取堆栈信息
- ValidationUtils：验证工具

# Druid配置

|                   配置                    |       缺省值       |                             说明                             |
| :---------------------------------------: | :----------------: | :----------------------------------------------------------: |
|                   name                    |                    | 配置这个属性的意义在于，如果存在多个数据源，监控的时候可以通过名字来区分开来。如果没有配置，将会生成一个名字，格式是："DataSource-" + System.identityHashCode(this). 另外配置此属性至少在1.0.5版本中是不起作用的，强行设置name会出错。[详情-点此处](http://blog.csdn.net/lanmo555/article/details/41248763)。 |
|                    url                    |                    | 连接数据库的url，不同数据库不一样。例如： mysql : jdbc:mysql://10.20.153.104:3306/druid2 oracle : jdbc:oracle:thin:@10.20.149.85:1521:ocnauto |
|                 username                  |                    |                      连接数据库的用户名                      |
|                 password                  |                    | 连接数据库的密码。如果你不希望密码直接写在配置文件中，可以使用ConfigFilter。[详细看这里](https://github.com/alibaba/druid/wiki/使用ConfigFilter) |
|              driverClassName              |  根据url自动识别   | 这一项可配可不配，如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName |
|                initialSize                |         0          | 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时 |
|                 maxActive                 |         8          |                        最大连接池数量                        |
|                  maxIdle                  |         8          |                 已经不再使用，配置了也没效果                 |
|                  minIdle                  |                    |                        最小连接池数量                        |
|                  maxWait                  |                    | 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。 |
|          poolPreparedStatements           |       false        | 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。 |
| maxPoolPreparedStatementPerConnectionSize |         -1         | 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100 |
|              validationQuery              |                    | 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。 |
|          validationQueryTimeout           |                    | 单位：秒，检测连接是否有效的超时时间。底层调用jdbc Statement对象的void setQueryTimeout(int seconds)方法 |
|               testOnBorrow                |        true        | 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 |
|               testOnReturn                |       false        | 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 |
|               testWhileIdle               |       false        | 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。 |
|                 keepAlive                 |  false （1.0.28）  | 连接池中的minIdle数量以内的连接，空闲时间超过minEvictableIdleTimeMillis，则会执行keepAlive操作。 |
|       timeBetweenEvictionRunsMillis       |  1分钟（1.0.14）   | 有两个含义： 1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明 |
|          numTestsPerEvictionRun           |  30分钟（1.0.14）  |      不再使用，一个DruidDataSource只支持一个EvictionRun      |
|        minEvictableIdleTimeMillis         |                    |               连接保持空闲而不被驱逐的最小时间               |
|            connectionInitSqls             |                    |                物理连接初始化的时候执行的sql                 |
|              exceptionSorter              | 根据dbType自动识别 |          当数据库抛出一些不可恢复的异常时，抛弃连接          |
|                  filters                  |                    | 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有： 监控统计用的filter:stat 日志用的filter:log4j 防御sql注入的filter:wall |
|               proxyFilters                |                    | 类型是List<com.alibaba.druid.filter.Filter>，如果同时配置了filters和proxyFilters，是组合关系，并非替换关系 |

# Lombok使用方法

## @Getter / @Setter

可以作用在类上和属性上，放在类上，会对所有的非静态(non-static)属性生成Getter/Setter方法，放在属性上，会对该属性生成Getter/Setter方法。并可以指定Getter/Setter方法的访问级别。

## @EqualsAndHashCode

默认情况下，会使用所有非瞬态(non-transient)和非静态(non-static)字段来生成equals和hascode方法，也可以指定具体使用哪些属性。

## @ToString

生成toString方法，默认情况下，会输出类名、所有属性，属性会按照顺序输出，以逗号分割。

## @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor

无参构造器、部分参数构造器、全参构造器，当我们需要重载多个构造器的时候，Lombok就无能为力了。

## @Data

包含@ToString, @EqualsAndHashCode, 所有属性的@Getter, 所有non-final属性的@Setter和@RequiredArgsConstructor的组合，通常情况下，基本上使用这个注解就足够了。

# 解决代码冲突

1. 点击 VCS
2. 点击 Update Project
3. 选择 Branch Default 和 Using Stash
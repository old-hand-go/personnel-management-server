# personnel-management-server
企业人员管理系统后端部分。



# 项目进度

- [ ] 用户管理：提供用户的相关配置，新增用户后，默认密码为123456

  gao

- [ ] 角色管理：对权限与菜单进行分配，可根据部门设置角色的数据权限

- [ ] 权限管理：权限细化到接口，可以理解成按钮权限

- [ ] 菜单管理：已实现菜单动态路由，后端可配置化，支持多级菜单

- [ ] 部门管理：可配置系统组织架构，树形表格展示

- [ ] 岗位管理：配置各个部门的职位

- [ ] 字典管理：可维护常用一些固定的数据，如：状态，性别等

- [ ] 操作日志：记录用户操作的日志

- [ ] 异常日志：记录异常日志，方便开发人员定位错误

- [ ] 系统缓存：使用jedis将缓存操作可视化，并提供对redis的基本操作，可根据需求自行扩展

- [x] SQL监控：采用druid监控数据库访问性能，默认用户名druid，密码druid

  dormirr

# 文件结构

## 模块

### 代码层

### 资源文件层

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
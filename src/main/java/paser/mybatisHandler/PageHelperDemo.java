package paser.mybatisHandler;

//@Configuration
//@AutoConfigureAfter(PageHelperAutoConfiguration.class)
//public class TestLogAutoConfiguration {
//    @Autowired
//    private List<SqlSessionFactory> sqlSessionFactoryList;
//
//    @PostConstruct
//    public void addMyInterceptor() {
//        EncryptionMybatisInterceptor e = new EncryptionMybatisInterceptor(new EncryptImpl());
//        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
//            sqlSessionFactory.getConfiguration().addInterceptor(e);
//        }
//    }
//}
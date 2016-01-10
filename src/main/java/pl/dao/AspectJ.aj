package pl.dao;

public aspect AspectJ {

     pointcut configurationHbm2ddl() : call(public static void pl.tools.Data.sendToDataBase());
     
     before() : configurationHbm2ddl() {
	  DAO.configurationHbm2ddlCreate();
     }
     
//     after() returning() : configurationHbm2ddl() {
//	  DAO.configurationHbm2ddlUpdate();
//     }
     
}

#**Log all springboot properties on application startup**


This simple project logs all springboot properties on Application startup **including the externalized ones** ([24-Externalized Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html) )

Instead of using Spring Environment object, wich does not guarantees that externalized properties values match the returned Map, we iterate over EnumerablePropertySources of the Environment. This resolves the values of the running Spring environment and guarantees that any overrided values will be correct. 

In this project we opted just to log the properties, but you can let your imagination go wild, and do whatever you want with those properties (as checking them against some known values and fail fast if they don't match or are not present, encripting some known specific properties in the log file).

To achieve the result we want, it is fundamental that our application context is fully initialized and ensure that we check the properties very early,actually before the execution of any other bean.

By implementing ***BeanFactoryPostProcessor*** and ***PriorityOrdered*** interfaces to our **BootParameters** configuration class, we can easily achieve this. (see [BeanFactoryPostProcessor](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/config/BeanFactoryPostProcessor.html) and [PriorityOrdered](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/PriorityOrdered.html) 

***

When running this sample application you can get something like


***
<sub><sup>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : hibernate.dialect.storage_engine:innodb<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : awt.toolkit:sun.awt.X11.XToolkit<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XMODIFIERS:@im=ibus<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : GDMSESSION:ubuntu<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.specification.version:1.8<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : sun.cpu.isalist:<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : sun.jnu.encoding:UTF-8<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : TEXTDOMAINDIR:/usr/share/locale/<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : DBUS_SESSION_BUS_ADDRESS:unix:path=/run/user/1000/bus<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : sun.arch.data.model:64<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : com.noshio.external.gateway.purchaseExpiration.minutes:30<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : DEFAULTS_PATH:/usr/share/gconf/ubuntu.default.path<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : SSH_AGENT_PID:6320<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.vendor.url:http://java.oracle.com/<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : user.country.format:PT<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LC_PAPER:pt_PT.UTF-8<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : SESSION_MANAGER:local/Y530:@/tmp/.ICE-unix/6191,unix/Y530:/tmp/.ICE-unix/6191<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : PWD:/home/martins/Storage/Projectos/Java/propertiesverifier<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : DERBY_HOME:/usr/lib/jvm/java-8-oracle/db<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LANGUAGE:en_US<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : sun.boot.library.path:/usr/lib/jvm/java-8-oracle/jre/lib/amd64<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : GJS_DEBUG_TOPICS:JS ERROR;JS LOG<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : sun.java.command:com.jumia.propertiesverifier.PropertiesverifierApplication<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : spring.liveBeansView.mbeanDomain:<br/>
2019-07-03 22:56:23.563  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.specification.vendor:Oracle Corporation<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.home:/usr/lib/jvm/java-8-oracle/jre<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : spring.datasource.password:xxxxx<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : GNOME_DESKTOP_SESSION_ID:this-is-deprecated<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : file.separator:/<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : GTK_MODULES:<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : CLUTTER_IM_MODULE:xim<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.specification.name:Java Platform API Specification<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.vm.specification.vendor:Oracle Corporation<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : spring.datasource.url:jdbc:mysql://172.20.0.14:3306/testDB<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : TEXTDOMAIN:im-confi<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LC_CTYPE:pt_PT.UTF-8<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : SHLVL:0<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : sun.management.compiler:HotSpot 64-Bit Tiered Compilers<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : JAVA_HOME:/usr/lib/jvm/java-8-oracle<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.runtime.version:1.8.0_201-b09<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : user.name:martins<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_CONFIG_DIRS:/etc/xdg/xdg-ubuntu:/etc/xdg<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_SEAT_PATH:/org/freedesktop/DisplayManager/Seat0<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_SESSION_ID:c2<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : file.encoding:UTF-8<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : com.noshio.external.gateway.retries:3<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LC_NAME:pt_PT.UTF-8<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.io.tmpdir:/tmp<br/>
2019-07-03 22:56:23.564  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.version:1.8.0_201<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_GREETER_DATA_DIR:/var/lib/lightdm-data/martins<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : DESKTOP_SESSION:ubuntu<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.vm.specification.name:Java Virtual Machine Specification<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : PID:31088<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.awt.printerjob:sun.print.PSPrinterJob<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_MENU_PREFIX:gnome-<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : sun.os.patch.level:unknown<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : QT_ACCESSIBILITY:1<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : spring.datasource.username:xxxxx<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.library.path:/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.vendor:Oracle Corporation<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.rmi.server.randomIDs:true<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : sun.io.unicode.encoding:UnicodeLittle<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : sun.desktop:gnome<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LC_MEASUREMENT:pt_PT.UTF-8<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XAUTHORITY:/home/martins/.Xauthority<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LC_TELEPHONE:pt_PT.UTF-8<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_DATA_DIRS:/usr/share/ubuntu:/usr/local/share:/usr/share:/var/lib/snapd/desktop<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : MANDATORY_PATH:/usr/share/gconf/ubuntu.mandatory.path<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : file.encoding.pkg:sun.io<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LC_TIME:pt_PT.UTF-8<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : GTK_IM_MODULE:ibus<br/>
2019-07-03 22:56:23.565  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.vm.vendor:Oracle Corporation<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : spring.jpa.hibernate.ddl-auto:update<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_CURRENT_DESKTOP:ubuntu:GNOME<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : user.timezone:Europe/Lisbon<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : QT4_IM_MODULE:xim<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.vm.specification.version:1.8<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : os.name:Linux<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LOGNAME:martins<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : IM_CONFIG_PHASE:2<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : sun.java.launcher:SUN_STANDARD<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : user.country:US<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : com.noshio.cellulant.transaction.poll.after.seconds:60<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : com.noshio.external.gateway.readTimeout:9500<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : spring.application.admin.enabled:true<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : com.sun.management.jmxremote:<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : spring.jpa.properties.hibernate.dialect:org.hibernate.dialect.MySQL5Dialect<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : SHELL:/bin/bash<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LC_ADDRESS:pt_PT.UTF-8<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : sun.cpu.endian:little<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : user.home:/home/martins<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : user.language:en<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : GIO_LAUNCHED_DESKTOP_FILE:/home/martins/.local/share/applications/jetbrains-idea.desktop<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : J2SDKDIR:/usr/lib/jvm/java-8-oracle<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : OLDPWD:/home/martins/.local/share/JetBrains/Toolbox/apps/IDEA-U/ch-0/191.7479.19/bin<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : com.noshio.external.gateway.connectionTimeout:9500<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : spring.output.ansi.enabled:always<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : J2REDIR:/usr/lib/jvm/java-8-oracle/jre<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_SESSION_PATH:/org/freedesktop/DisplayManager/Session0<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.awt.graphicsenv:sun.awt.X11GraphicsEnvironment<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.awt.headless:true<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_SESSION_DESKTOP:ubuntu<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LC_IDENTIFICATION:pt_PT.UTF-8<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LC_MONETARY:pt_PT.UTF-8<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : QT_IM_MODULE:ibus<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : spring.jmx.enabled:true<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : path.separator::<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LANG:en_US.UTF-8<br/>
2019-07-03 22:56:23.566  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_SESSION_TYPE:x11<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : os.version:4.15.0-54-generic<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.endorsed.dirs:/usr/lib/jvm/java-8-oracle/jre/lib/endorsed<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.runtime.name:Java(TM) SE Runtime Environment<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : DISPLAY::0<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : spring.beaninfo.ignore:true<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.vm.name:Java HotSpot(TM) 64-Bit Server VM<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.vendor.url.bug:http://bugreport.sun.com/bugreport/<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : GDM_LANG:en_US<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : user.dir:/home/martins/Storage/Projectos/Java/propertiesverifier<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : os.arch:amd64<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : GPG_AGENT_INFO:/run/user/1000/gnupg/S.gpg-agent:0:1<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : user.language.format:pt<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : USER:martins<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : com.noshio.external.gateway.instructions.displaytimeout.milisecods:120000<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : WINEARCH:win64<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : GIO_LAUNCHED_DESKTOP_FILE_PID:18364<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : LC_NUMERIC:pt_PT.UTF-8<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : GJS_DEBUG_OUTPUT:stderr<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : SSH_AUTH_SOCK:/run/user/1000/keyring/ssh<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_SEAT:seat0<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.vm.info:mixed mode<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.vm.version:25.201-b09<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.ext.dirs:/usr/lib/jvm/java-8-oracle/jre/lib/ext:/usr/java/packages/lib/ext<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : GNOME_SHELL_SESSION_MODE:ubuntu<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_VTNR:7<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : XDG_RUNTIME_DIR:/run/user/1000<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : java.class.version:52.0<br/>
2019-07-03 22:56:23.567  INFO 31088 --- [           main] c.j.p.boot.BootParameters                : HOME:/home/martins<br/>

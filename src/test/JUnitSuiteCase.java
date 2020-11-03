package test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({TesteValidacaoCPF.class, TesteValidacaoEmail.class, TesteDaoFuncionario.class,})
@IncludeTags({"success"})
//@IncludeTags({"failure"})
//@IncludeTags({"error"})

public class JUnitSuiteCase {

	

}

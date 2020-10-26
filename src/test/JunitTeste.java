package test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses; 

@RunWith(JUnitPlatform.class)
@SuiteClasses({TesteDaoFuncionario.class, TesteValidacaoCPF.class})
public class JunitTeste {

}

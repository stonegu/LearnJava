import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;

public class GuiceTester {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);
        editor.makeSpellCheck();
    }
}

//Binding Module
class TextEditorModule extends AbstractModule {
    @Override

    protected void configure() {
        // binding : this tells Guice that whenever it sees a dependency on a SpellChecker,
        //           it should satisfy the dependency using a SpellCheckerImpl
        bind(SpellChecker.class).to(SpellCheckerImpl.class);
        // AOP : there are couple limitations for Guice AOP, for example:
        //       instances must be created by Guice
        //       method must be public, package-private, or protected, and non-final
        // in this case we match any class, but only the methods with @CallTracker annotation
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(CallTracker.class),
                new CallTrackerService());
    }
}

class CallTrackerService implements MethodInterceptor  {
    @Override

    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before " + invocation.getMethod().getName());
        Object result = invocation.proceed();
        System.out.println("After " + invocation.getMethod().getName());
        return result;
    }
}
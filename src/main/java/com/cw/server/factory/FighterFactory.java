package com.cw.server.factory;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


public class FighterFactory {

	private FighterFactory() {}
	
	private final static File root = new File( "actiondoer_classes" );
	private static URLClassLoader classLoader;
	static {
		if (!root.exists())
			root.mkdirs();
		try {
			classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static final String templateFighter =
			"import com.cw.models.Fighter;\n" +
			"import com.cw.models.GameEnvironment;\n" +
			"import com.cw.server.factory.ActionDoer;\n" +
			"\n" +
			"public class %s implements ActionDoer {\n" +
			"    public void doAction(Fighter self, GameEnvironment env) {\n" +
			"        %s\n" +
			"    }\n" +
			"}\n";

	private static final String templateContent = "" +
			"import com.cw.codetoaction.CertainFighter;\r\n" +
			"import com.cw.codetoaction.GameEnvironment;\r\n" +
			"import com.cw.codetoaction.ActionDoer;\r\n" +
			"import com.cw.models.Fighter;\r\n" +
			"public class %s extends ActionDoer {\r\n" + 
			"    public void execute(CertainFighter self, GameEnvironment env) {\r\n" + 
			"        %s\r\n" + 
			"    }\r\n" + 
			"}\r\n";
	
	public static ActionDoer getActionDoer(String suffix, String code) {
		//System.out.println(templateContent);
		if (isSafeCode(code)) {
			if (writeActionDoer(suffix, code)) {
				if (compileActionDoer(suffix)) {
					System.out.println( "Code contains no errors. " );
					return getActionDoer(suffix);
				} else {
					System.out.println( "Error in your code! " );
					return null; // TODO check
				}
			} else
				System.out.println("failed to write ActionDoer");
		} else {
			System.out.println("aborted: your code is not safe, your actions "
					+ "will be reported");
			//report(code);
		}
		return null;
	}
	
	private static boolean compileActionDoer(String suffix) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		File sourceFile = new File( getFilePath(suffix) );
		if (compiler == null) {
			System.out.println("You probably didn't specify to run this program"
					+ " from JDK instead of JRE. Do this and try again.");
			System.exit(1);
		}
		return 0 == compiler.run(null, null, null, sourceFile.getPath());
	}

	private static ActionDoer getActionDoer(String suffix) {
		ActionDoer res = null;
		try {
			Class<ActionDoer> cls = (Class<ActionDoer>) Class.forName( getClassName(suffix) , true, classLoader);
			res = cls.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	private static boolean writeActionDoer(String suffix, String code) {
		File f = new File(getFilePath(suffix));
		String normalizedCode = code.replace("\n", ";\r\n        ");
		if (normalizedCode.length() >= 10)
			normalizedCode = normalizedCode.substring(0, normalizedCode.length() - 10);
		try ( BufferedWriter w = new BufferedWriter(new FileWriter(f)) ) {
			w.write(String.format(templateContent, getClassName(suffix), normalizedCode));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		//System.out.println(f.getAbsolutePath());
		return true;
	}
	
	private static String getClassName(String suffix) { 
		return String.format("ActionDoer%s", suffix); 
	}
	
	private static String getFilePath(String suffix) { 
		return String.format(root.getPath() + File.separator + "ActionDoer%s.java", suffix); 
	}
	
	private static boolean isSafeCode(String code) {
		return true;
	}

}

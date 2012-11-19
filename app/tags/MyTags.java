package tags;

import groovy.lang.Closure;
import play.data.validation.Validation;
import play.exceptions.TagInternalException;
import play.exceptions.TemplateExecutionException;
import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;

import java.io.PrintWriter;
import java.util.Map;

public class MyTags extends FastTags {

	/**
	 * Tag to replace the default ErrorClass replacing the string returned by the Twitter Bootstrap error class
	 * @param args
	 * @param body
	 * @param out
	 * @param template
	 * @param fromLine
	 */
	public static void _twErrorClass(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {
        if (args.get("arg") == null) {
            throw new TemplateExecutionException(template.template, fromLine, "Please specify the error key", new TagInternalException("Please specify the error key"));
        }
        if (Validation.hasError(args.get("arg").toString())) {
            out.print("error");
        }
    }
	
}

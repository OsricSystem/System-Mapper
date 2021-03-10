package osricsystem.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonConverter{
	private static final ObjectMapper mapper=new ObjectMapper();
	private static final ObjectWriter writerDefault;
	private static final ObjectWriter writerPretty;

	private JsonConverter(){}

	static{
		DefaultPrettyPrinter printer=new DefaultPrettyPrinter();
		DefaultIndenter indenter=new DefaultIndenter("\t", DefaultIndenter.SYS_LF);
		printer.indentObjectsWith(indenter);
		printer.indentArraysWith(indenter);

		writerDefault=mapper.writer();
		writerPretty=mapper.writer(printer);
	}

	// Serialization
	public static <T> T fromJson(String input, Class<T> cls) throws JsonMappingException, JsonProcessingException{
		return mapper.readValue(input, cls);
	}
	public static String toJson(Object value, boolean prettyPrint) throws JsonProcessingException{
		return (prettyPrint? writerPretty: writerDefault).writeValueAsString(value);
	}
}

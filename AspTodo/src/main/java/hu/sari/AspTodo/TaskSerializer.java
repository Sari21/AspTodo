package hu.sari.AspTodo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hu.sari.AspTodo.Model.Task;

import java.io.IOException;

public class TaskSerializer extends StdSerializer<Task> {

    protected TaskSerializer(Class<Task> t) {
        super(t);
    }

    protected TaskSerializer(JavaType type) {
        super(type);
    }

    protected TaskSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    protected TaskSerializer(StdSerializer<?> src) {
        super(src);
    }

    @Override
    public void serialize(
            Task task, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", task.getId());
        jgen.writeStringField("title", task.getTitle());
        jgen.writeStringField("description", task.getDescription());
        jgen.writeBooleanField("isDone", task.isDone());
        jgen.writeObjectField("project", task.getProject());
        jgen.writeNumberField("user_id", task.getUser().getId());
        jgen.writeEndObject();
    }

}

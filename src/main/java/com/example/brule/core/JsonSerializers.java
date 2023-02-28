package com.example.brule.core;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class JsonSerializers {

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> {
			// jpa entity serializers
			builder.serializers(EntityBaseSerializer.instance);

			// datetime formatter
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			// datetime deserializers
			builder.deserializers(new LocalDateDeserializer(dateFormatter));
			builder.deserializers(new LocalDateTimeDeserializer(dateTimeFormatter));

			// datetime serializers
			builder.serializers(new LocalDateSerializer(dateFormatter));
			builder.serializers(new LocalDateTimeSerializer(dateTimeFormatter));
		};
	}

}

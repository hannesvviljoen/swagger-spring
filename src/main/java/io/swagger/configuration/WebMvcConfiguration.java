package io.swagger.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.threetenbp.ThreeTenModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZonedDateTime;

import java.util.List;

@Configuration
class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for(HttpMessageConverter<?> converter: converters) {
            if(converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper mapper = ((MappingJackson2HttpMessageConverter)converter).getObjectMapper();
                ThreeTenModule module = new ThreeTenModule();
                module.addDeserializer(Instant.class, CustomInstantDeserializer.INSTANT);
                module.addDeserializer(OffsetDateTime.class, CustomInstantDeserializer.OFFSET_DATE_TIME);
                module.addDeserializer(ZonedDateTime.class, CustomInstantDeserializer.ZONED_DATE_TIME);
                mapper.registerModule(module);
                mapper.setFilterProvider(new SimpleFilterProvider().addFilter("individualFilter", SimpleBeanPropertyFilter.serializeAll()));
            }
        }
    }
}

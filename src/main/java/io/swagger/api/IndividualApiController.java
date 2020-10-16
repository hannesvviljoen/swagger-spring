package io.swagger.api;

import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.api.services.IndividualService;
import io.swagger.model.dto.Individual;
import io.swagger.model.dto.IndividualCreate;
import io.swagger.model.dto.IndividualUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-08T11:42:54.708Z")

@Controller
public class IndividualApiController implements IndividualApi {

    private static final Logger log = LoggerFactory.getLogger(IndividualApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final IndividualService individualService;

    @org.springframework.beans.factory.annotation.Autowired
    public IndividualApiController(ObjectMapper objectMapper, HttpServletRequest request, IndividualService individualService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.individualService = individualService;
    }

    public ResponseEntity<Individual> createIndividual(@ApiParam(value = "The Individual to be created" ,required=true )  @Valid @RequestBody IndividualCreate individual) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Individual>(individualService.create(individual),HttpStatus.OK);
            } catch (ApiException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                throw new RuntimeException(e);
            }
        }

        return new ResponseEntity<Individual>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteIndividual(@ApiParam(value = "Identifier of the Individual",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        try {
            individualService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Individual>> listIndividual(@ApiParam(value = "Comma-separated properties to be provided in response") @Valid @RequestParam(value = "fields", required = false) String fields,@ApiParam(value = "Requested index for start of resources to be provided in response") @Valid @RequestParam(value = "offset", required = false) Integer offset,@ApiParam(value = "Requested number of resources to be provided in response") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {

                List<Individual> listAll = null;
                if(offset != null && limit != null)
                    listAll = individualService.listAll(new PageRequest(offset, limit));
                else
                    listAll = individualService.listAll();

                if(fields == null || fields.equals("*")) {
                    return new ResponseEntity<List<Individual>>( listAll ,HttpStatus.OK);
                }else {
                    List<String> stringList = Arrays.asList(fields.split(","));
                    Set<String> filterSet = new HashSet<String>();
                    for (String x : stringList)
                        filterSet.add(x);

                    SimpleFilterProvider filterProvider = new SimpleFilterProvider();
                    filterProvider.addFilter("individualFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept(filterSet));


                    ObjectMapper om = new ObjectMapper();
                    om.setFilterProvider(filterProvider);
                    return new ResponseEntity<List<Individual>>(objectMapper.readValue(om.writeValueAsString(listAll), List.class), HttpStatus.OK);
                }

            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Individual>>(HttpStatus.INTERNAL_SERVER_ERROR);

            }
        }

        return new ResponseEntity<List<Individual>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Individual> patchIndividual(@ApiParam(value = "Identifier of the Individual",required=true) @PathVariable("id") String id,@ApiParam(value = "The Individual to be updated" ,required=true )  @Valid @RequestBody IndividualUpdate individual) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Individual>(individualService.update(id ,individual),HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Individual>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Individual>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Individual> retrieveIndividual(@ApiParam(value = "Identifier of the Individual",required=true) @PathVariable("id") String id,@ApiParam(value = "Comma-separated properties to provide in response") @Valid @RequestParam(value = "fields", required = false) String fields) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Individual individual = individualService.findById(id);

                if(fields == null || fields.equals("*")) {
                    return new ResponseEntity<Individual>( individual ,HttpStatus.OK);
                }

                List<String> stringList = Arrays.asList(fields.split(","));
                Set<String> filterSet = new HashSet<String>();
                for (String x : stringList)
                    filterSet.add(x);

                SimpleFilterProvider filterProvider = new SimpleFilterProvider();
                filterProvider.addFilter("individualFilter",
                        SimpleBeanPropertyFilter.filterOutAllExcept(filterSet));


                ObjectMapper om = new ObjectMapper();
                om.setFilterProvider(filterProvider);
                return new ResponseEntity<Individual>(objectMapper.readValue(om.writeValueAsString(individual), Individual.class), HttpStatus.OK);



            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Individual>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Individual>(HttpStatus.NOT_IMPLEMENTED);
    }

}

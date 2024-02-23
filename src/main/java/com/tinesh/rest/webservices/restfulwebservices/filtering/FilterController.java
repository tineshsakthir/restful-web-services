package com.tinesh.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilterController {
    /**
     * Jackson json converter provides some annotaations to implement filtering in two ways
     * 1. Static filtering (@JsonIgnore and @JsonIgnoreProperties)
     * 2. Dynamic filtering(@JsonFilter) and using multiple functions provided by java like
     * a) MappingJacksonValue (from Spring FrameWork)
     * b) SimpleBeanPropertyFilter (form jackson API)
     * c) FilterProvider (from jackson API)
     * **/
    @RequestMapping("/staticFiltering")
    public SomeBean staticFiltering(){
        return new SomeBean("value1","value2","value3") ;
    }
    @RequestMapping("/staticFilteringForList")

    public List<SomeBean> staticFiltering_list(){
        return Arrays.asList( new SomeBean("value1","value2","value3"), new SomeBean("value1","value2","value3"), new SomeBean("value1","value2","value3")) ;
    }

    @RequestMapping("/dynamicFiltering")
    public MappingJacksonValue dynamicFiltering(){

        SomeBeanForDynamicFiltering someBeanForDynamicFiltering = new SomeBeanForDynamicFiltering("value1","value2","value3") ;
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeanForDynamicFiltering) ;
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("property1","property2") ;
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeDynamicFilterLearning",simpleBeanPropertyFilter) ;
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue ;
    }

    @RequestMapping("/dynamicFilteringForList")
    public List<SomeBeanForDynamicFiltering> dynamicFilteringList (){
        return Arrays.asList(new SomeBeanForDynamicFiltering("value1","value2","value3") , new SomeBeanForDynamicFiltering("value1","value2","value3"), new SomeBeanForDynamicFiltering("value1","value2","value3")) ;
    }


}

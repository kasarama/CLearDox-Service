package com.fin.cleardoxservice.resources;

import com.fin.cleardoxservice.model.UserDetailsRequestModel;
import com.fin.cleardoxservice.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@RestController
@RequestMapping("/file")
public class FileResource {
    @Autowired
    private  RestTemplate restTemplate;


    @Value("${cleardox.login}")
    private  String loginURL;

    @Value("${cleardox.postfile}")
    private String postFileUrl;



/*
    @GetMapping("/{foid}")
    public String getJobInfo(@PathParam("foid") String foid) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();


        converter.setSupportedMediaTypes(
                Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM}));

        restTemplate.setMessageConverters(Arrays.asList(converter, new FormHttpMessageConverter()));
        File book = restTemplate.getForObject(FINAL_URL, File.class);

        return restTemplate;
    }
    */

    TokenUtils tokenUtils = new TokenUtils();

    @PostMapping("/{foid}")
    public String uploadToCleardox(@PathVariable("foid") String foid, @RequestBody UserDetailsRequestModel requestUserDetails) {
        String token = tokenUtils.getTokenFromClearDox(loginURL, restTemplate, requestUserDetails).getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + token);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();

        body.add("data", getTestFile());
        body.add("name", getTestFile().getFilename());

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .postForEntity(postFileUrl, requestEntity, String.class);

        return response.toString();
    }

    private FileSystemResource getTestFile() {
        return new FileSystemResource(new File("src/main/resources/static/file1.pdf"));
    }

}



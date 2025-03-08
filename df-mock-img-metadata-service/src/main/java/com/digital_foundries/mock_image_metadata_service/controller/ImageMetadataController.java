package com.digital_foundries.mock_image_metadata_service.controller;


import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataDto;
import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataEntity;
import com.digital_foundries.mock_image_metadata_service.service.ImageMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.http.HttpResponse;

@Controller
class ImageMetadataController{



        @Autowired
        ImageMetadataService imageMetadataService;

        @PostMapping("heyy")
        public ResponseEntity<ImageMetadataDto> postDto(@RequestBody ImageMetadataDto imageMetadataDto){
            ImageMetadataEntity createdEntity = imageMetadataService.createImageMetadata(imageMetadataDto);
            if(createdEntity == null){
                return ResponseEntity.badRequest().body(null);
            }

            return ResponseEntity.ok(""); // HTTP 200 OK
        }




}
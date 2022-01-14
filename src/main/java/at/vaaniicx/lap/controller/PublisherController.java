package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.PublisherDTO;
import at.vaaniicx.lap.model.entity.PublisherEntity;
import at.vaaniicx.lap.model.mapper.PublisherMapper;
import at.vaaniicx.lap.model.request.management.publisher.UpdatePublisherRequest;
import at.vaaniicx.lap.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    @ResponseBody
    public List<PublisherDTO> getAll() {
        return publisherService.getAllPublisher().stream().map(PublisherMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PublisherDTO getById(@PathVariable("id") Long publisherId) {
        return PublisherMapper.toDto(publisherService.getPublisherById(publisherId));
    }

    @PostMapping("/update")
    public PublisherEntity updatePublisher(@RequestBody @Validated UpdatePublisherRequest request) {
        PublisherEntity publisher = publisherService.getPublisherById(request.getId());

        publisher.setPublisher(request.getPublisher());

        return publisherService.updatePublisher(publisher);
    }
}

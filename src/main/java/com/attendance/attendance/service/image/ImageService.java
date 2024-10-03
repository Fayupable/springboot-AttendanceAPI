package com.attendance.attendance.service.image;

import com.attendance.attendance.dto.ImageDto;
import com.attendance.attendance.entity.Image;
import com.attendance.attendance.entity.Person;
import com.attendance.attendance.exceptions.ResourceNotFoundException;
import com.attendance.attendance.repository.IImageRepository;
import com.attendance.attendance.service.person.IPersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {
    private final IImageRepository imageRepository;
    private final IPersonService personService;


    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image not found"));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ResourceNotFoundException("Image not found with id: " + id);
        });

    }

    @Override
    public List<ImageDto> saveImage(List<MultipartFile> files, Long personId) {
        Person person = personService.getUserById(personId);
        List<ImageDto> savedImageDto = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setPerson(person);

                String buildDownloadUrl = "/api/v1/images/image/download/";
                String downloadUrl = buildDownloadUrl + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());
                imageRepository.save(savedImage);

                ImageDto imageDto = new ImageDto();
                imageDto.setId(savedImage.getId());
                imageDto.setFileName(savedImage.getFileName());
                savedImageDto.add(imageDto);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return savedImageDto;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("Image not found"));
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

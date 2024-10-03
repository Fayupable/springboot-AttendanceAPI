package com.attendance.attendance.service.image;

import com.attendance.attendance.dto.ImageDto;
import com.attendance.attendance.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDto> saveImage(List<MultipartFile> files, Long personId);

    void updateImage(MultipartFile file, Long imageId);
}

package com.example.challenge.model.dto;

import com.example.challenge.model.Folder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FolderDto {

    private Long id;
    private String name;

    public static FolderDto folderToDto(Folder folder){
        return FolderDto.builder()
                .id(folder.getId())
                .name(folder.getName())
                .build();
    }

}

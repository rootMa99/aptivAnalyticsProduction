package com.aptiv.dataAnalytics.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FileRest {

    private String fileId;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

}

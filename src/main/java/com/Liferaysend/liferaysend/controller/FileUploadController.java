package com.Liferaysend.liferaysend.controller;

//import com.Liferaysend.liferaysend.model.client;
import com.Liferaysend.liferaysend.model.client;
import com.Liferaysend.liferaysend.service.serviceimpl.ClientServiceImpl;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.Liferaysend.liferaysend.service.FileUploadService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private ClientServiceImpl clientService;
    @GetMapping("/welcoming")
    public String welcome(Model model)
    {
      //  model.addAttribute("client", new client());

        return "form";
    }
    @PostMapping("/upload")
    public String uploadLocal(
            Model model,
            @RequestParam("clientName") String clientname,
            @RequestParam("clientPrenom") String clientprenom,
            @RequestParam("clientState") String clientstate,
            @RequestParam("clientCin") String clientcin,
            @RequestParam("clientVille") String clientville,
            @RequestParam("clientDOB") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate clientdob,
            @RequestParam("fichCv") MultipartFile cvFile,
            @RequestParam("fichCertificat") MultipartFile certificatFile,
            @RequestParam("fichCin") MultipartFile cinFile,
            @RequestParam("clientEmail") String clientemail

            )
    {
        // fileUploadService.uploadToLocal(cvFile, "/Users/mac/Desktop/NetopiaStorage/" + clientstate + "/" + clientcin + "_" + clientprenom + "_" + clientname+"_CV");

        List<MultipartFile> files=new ArrayList<MultipartFile>();
        if(clientstate.equals("Employee"))
        {
            files.add(cvFile);
            files.add(cinFile);
        }
        if(clientstate.equals("Etudiant"))
        {
            files.add(certificatFile);
        }


        for(MultipartFile myfile:files)
        {
            String path= "/Users/mac/Desktop/NetopiaStorage/" + clientstate + "/" + clientcin + "_" + clientprenom + "_" + clientname+myfile.getName();
            fileUploadService.uploadToLocal(cvFile, path);

                File file = new File(path);
                String url = "http://localhost:8080/api/jsonws/dlapp/add-file-entry";
                String repositoryId = "20121";
                String folderId = clientstate.equals("Employee") ? "37702" : "37517";
                String sourceFileName=myfile.getOriginalFilename();
                String mimeType=myfile.getContentType();
                String title= clientcin+"_"+clientprenom+"_"+clientname+myfile.getName();
                String description= "";
                String changeLog= "";
                String fileName=myfile.getOriginalFilename();
                String usernamePassword  = "abdelkarim.imghi@gmail.com:83126052Ab";

                try{
                    HttpPost httpPost = new HttpPost(url);
                    String base64Encoded = DatatypeConverter.printBase64Binary(
                            usernamePassword.getBytes("UTF-8"));
                    httpPost.setHeader("Authorization", "Basic "  +  base64Encoded);
                    MultipartEntityBuilder multiPartEntity = MultipartEntityBuilder.create();
                    multiPartEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                    multiPartEntity.addPart("repositoryId",
                            new StringBody(repositoryId, ContentType.TEXT_PLAIN));
                    multiPartEntity.addPart("folderId",
                            new StringBody(folderId, ContentType.TEXT_PLAIN));
                    multiPartEntity.addPart("sourceFileName",
                            new StringBody(sourceFileName,ContentType.TEXT_PLAIN));
                    multiPartEntity.addPart("mimeType",
                            new StringBody(mimeType,ContentType.TEXT_PLAIN));
                    multiPartEntity.addPart("title",
                            new StringBody(title, ContentType.TEXT_PLAIN));
                    multiPartEntity.addPart("description",
                            new StringBody(description,ContentType.TEXT_PLAIN));
                    multiPartEntity.addPart("changeLog",
                            new StringBody(changeLog,ContentType.TEXT_PLAIN));
                    FileBody fileBody =
                            new FileBody(file, ContentType.APPLICATION_OCTET_STREAM,fileName );

                    multiPartEntity.addPart("file", fileBody);
                    httpPost.setEntity(multiPartEntity.build()) ;
                    CloseableHttpResponse response = (CloseableHttpResponse) HttpClientBuilder.
                            create().build().execute(httpPost);
                    System.out.println("Sent sucess Karim ");
                }
                catch(Exception e)
                {
                    System.out.println("Something went wrong karim !!");
                }


        }
        /*client client =new client( clientcin,
                clientville,
                clientname,
                clientprenom,
                clientstate,
                clientcin+"_"+clientprenom+"_"+clientname,
                clientdob
        );
        clientService.StoreClient(client);*/
        model.addAttribute("clientname",clientname);

        return "greetings";


    }

}

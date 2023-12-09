package net.prescent.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

/*
public class AITestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AIModelService aiModelService;

    @Mock
    private AITestService aiTestService;

    @InjectMocks
    private AITestController aiTestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(aiTestController).build();
    }

    @Test
    public void testUploadAndProcessImage() throws Exception {
        // Create a mock multipart file
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test image content".getBytes());

        // Mock the behavior of void methods
        doNothing().when(aiModelService).uploadFileToS3(any(MultipartFile.class), any(String.class));
        given(aiModelService.getFileUrl("uploads/test.jpg")).willReturn("http://example.com/uploads/test.jpg");

        // Set up mock responses for additional images
        List<Object> dummyImages = new ArrayList<>();
        dummyImages.add(Map.of("url", "http://example.com/uploads/image2.jpg")); // URL only for image2.jpg
        dummyImages.add(new ImageInfo("http://example.com/uploads/image3.jpg", "Daisy", "Lovely"));
        dummyImages.add(new ImageInfo("http://example.com/uploads/image4.jpg", "Gerbera", "Mysterious"));
        dummyImages.add(new ImageInfo("http://example.com/uploads/image5.jpg", "Rose", "Love")); // Assuming you want to test image5.jpg too
        dummyImages.add(new ImageInfo("http://example.com/uploads/image6.jpg", "Lily", "Purity")); // Assuming you want to test image6.jpg too

        given(aiTestService.processAdditionalImages()).willReturn(dummyImages);

        // Perform the test
        mockMvc.perform(multipart("/pslens").file(file))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].url").value("http://example.com/uploads/test.jpg"))
                .andExpect(jsonPath("$[1].url").value("http://example.com/uploads/image2.jpg")) // URL only for image2.jpg
                .andExpect(jsonPath("$[2].url").value("http://example.com/uploads/image3.jpg"))
                .andExpect(jsonPath("$[2].name").value("Daisy"))
                .andExpect(jsonPath("$[2].meaning").value("Lovely"))
                .andExpect(jsonPath("$[3].url").value("http://example.com/uploads/image4.jpg"))
                .andExpect(jsonPath("$[3].name").value("Gerbera"))
                .andExpect(jsonPath("$[3].meaning").value("Mysterious"))
                .andExpect(jsonPath("$[4].url").value("http://example.com/uploads/image5.jpg")) // For image5.jpg
                .andExpect(jsonPath("$[4].name").value("Rose"))
                .andExpect(jsonPath("$[4].meaning").value("Love"))
                .andExpect(jsonPath("$[5].url").value("http://example.com/uploads/image6.jpg")) // For image6.jpg
                .andExpect(jsonPath("$[5].name").value("Lily"))
                .andExpect(jsonPath("$[5].meaning").value("Purity"));
    }
}

 */

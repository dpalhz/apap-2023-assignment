package apap.ti.silogistik2106751543;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import com.github.javafaker.Faker;

import apap.ti.silogistik2106751543.dto.GudangMapper;
import apap.ti.silogistik2106751543.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751543.model.Gudang;
import apap.ti.silogistik2106751543.service.GudangService;
import jakarta.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Silogistik2106751543Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106751543Application.class, args);
	}



	// CommandLineRunner digunakan untuk execute code saat spring pertama kali start up
	// @Bean
	// @Transactional
	// CommandLineRunner run(GudangService gudangService, GudangMapper gudangMapper){
	// 	return args -> {
	// 		var faker = new Faker(new Locale("in-ID"));

		
    //     for (int i = 0; i < 10; i++) {
    //         String namaGudang = faker.company().name();
    //         String alamatGudang = faker.address().streetAddress();

    //         CreateGudangRequestDTO gudangRequestDTO = new CreateGudangRequestDTO();
    //         gudangRequestDTO.setNamaGudang(namaGudang);
    //         gudangRequestDTO.setAlamatGudang(alamatGudang);

    //         // Anda bisa menambahkan atribut lain sesuai kebutuhan

    //         // Panggil service untuk membuat gudang
	// 		var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangRequestDTO);
    //         gudangService.saveGudang(gudang);

    //         // Tambahkan gudang ke daftar gudang jika diperlukan
    //         // misalnya: gudangService.addGudangToDaftarGudang(gudang);
    //     }
    // };
	// }
}



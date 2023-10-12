package apap.ti.silogistik2106751543;

import java.util.Locale;
import com.github.javafaker.Faker;

import apap.ti.silogistik2106751543.dto.GudangMapper;
import apap.ti.silogistik2106751543.dto.KaryawanMapper;
import apap.ti.silogistik2106751543.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751543.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751543.service.GudangService;
import apap.ti.silogistik2106751543.service.KaryawanService;
import jakarta.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.Random;


@SpringBootApplication
public class Silogistik2106751543Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106751543Application.class, args);
	}



	// CommandLineRunner digunakan untuk execute code saat spring pertama kali start up
	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, GudangMapper gudangMapper, KaryawanMapper karyawanMapper, KaryawanService karyawanService){
		return args -> {
			var faker = new Faker(new Locale("in-ID"));
            Random random = new Random();

		
        for (int i = 0; i < 10; i++) {
            String namaGudang = faker.company().name();
            String alamatGudang = faker.address().streetAddress();

            CreateGudangRequestDTO gudangRequestDTO = new CreateGudangRequestDTO();
            gudangRequestDTO.setNamaGudang(namaGudang);
            gudangRequestDTO.setAlamatGudang(alamatGudang);

			var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangRequestDTO);
            gudangService.saveGudang(gudang);

          
        }

        // Todo buat karyawan dummy

        // Buat karyawan dummy
        for (int i = 0; i < 10; i++) {
            String namaKaryawan = faker.name().lastName();
                if (namaKaryawan.length() > 12) {
                    namaKaryawan = namaKaryawan.substring(0, 12);
                }

            // Batasan tahun kelahiran minimal adalah 1990
            int tahunKelahiran = 1990 + random.nextInt(30); // Acak tahun antara 1990 dan 2019

            int bulanKelahiran = 1 + random.nextInt(12); // Acak bulan antara 1 dan 12
            int hariKelahiran = 1 + random.nextInt(28); // Acak hari antara 1 dan 28
            int jenisKelamin = faker.number().numberBetween(1, 3);

            Date tanggalLahir = new Date(tahunKelahiran - 1900, bulanKelahiran - 1, hariKelahiran);

            CreateKaryawanRequestDTO karyawanRequestDTO = new CreateKaryawanRequestDTO();
            karyawanRequestDTO.setNama(namaKaryawan);
            karyawanRequestDTO.setTanggalLahir(tanggalLahir);
            karyawanRequestDTO.setJenisKelamin(jenisKelamin);

            var karyawan = karyawanMapper.createGudangRequestDTOToGudang(karyawanRequestDTO);
            karyawanService.save(karyawan);
        }
    };
	}
}



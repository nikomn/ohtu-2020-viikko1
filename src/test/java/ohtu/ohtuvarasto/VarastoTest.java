package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoEiYlitayty() {
        varasto.lisaaVarastoon(9);
        varasto.lisaaVarastoon(2);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoEiMeneMiinukselle() {
        varasto.lisaaVarastoon(2);
        varasto.otaVarastosta(3);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoaEiVoiAlustaaNegatiivisellaArvolla() {
        Varasto v = new Varasto(-1.0);
        Varasto v2 = new Varasto(-1.0, 0);

        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, v2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoonEiVoiLisataNegatiivista() {
        varasto.lisaaVarastoon(-1.0);

        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoiOttaaNegatiivista() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-1.0);

        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastolleVoiMaaritellaAlkusaldon() {
        Varasto v = new Varasto(10, 2);

        assertEquals(2, v.getSaldo(), vertailuTarkkuus);
        assertEquals(8, v.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastolleEiVoiMaaritellaNegatiivistaAlkusaldoa() {
        Varasto v = new Varasto(10, -2);

        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
        assertEquals(10, v.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuuttaSuurempiAlkusaldoEiYlitayta() {
        Varasto v = new Varasto(10, 12);

        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
        assertEquals(0, v.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tulostuksenMuotoOikein() {
        // "saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
        varasto.lisaaVarastoon(2);

        assertTrue("Tulostus virheellisessä muodossa:" + varasto.toString(), varasto.toString().equals("saldo = 2.00, vielä tilaa 8.0"));
    }
    
    

}
package br.com.camila.cadastropessoa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração para WEB CORS para os endpoints
 * Configuração para habilitar CORS (Cross-Origin Resource Sharing) na aplicação.
 * <p>
 * Esta configuração permite que solicitações de diferentes origens (domínios) possam acessar os endpoints da aplicação
 * que correspondem ao padrão especificado. É especialmente útil quando o frontend e o backend estão em domínios diferentes.
 * </p>
 *
 * @author Camila
 * @since V1
 */
@Configuration
public class WebConfigCORS implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry
                .addMapping("/pessoas/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowedHeaders("*");
    }
}

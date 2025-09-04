package pfa.gestionsalle.dto;

public class UserDto {

        private String username;
        private String email; // optionnel si tu veux afficher plus tard

        // Constructeur
        public UserDto(String username, String email) {
            this.username = username;
            this.email = email;
        }

        // Getter et Setter
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
}

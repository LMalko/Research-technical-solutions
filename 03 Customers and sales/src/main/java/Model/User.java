package Model;

public abstract class User {
        protected int id;
        protected String name;
        String surname;
        String login;
        String password;
        private String status;

        User(String name, String surname, String password, String status){
                this.name = name;
                this.surname = surname;
                this.password = password;
                this.status = status;
        }

        User(int id, String name, String surname, String password, String status){
                this.id = id;
                this.name = name;
                this.surname = surname;
                this.password = password;
                this.status = status;
        }

        public void setName(String name){
                this.name = name;
        }

        public void setId(int id){
                this.id = id;
        }

        public String getName(){
                return this.name;
        }

        public String getSurname(){
                return this.surname;
        }

        public int getId(){
                return this.id;
        }

        public String getLogin(){
                return this.login;
        }

        public String getPassword(){
                return this.password;
        }

        public String getStatus(){
                return this.status;
        }

}

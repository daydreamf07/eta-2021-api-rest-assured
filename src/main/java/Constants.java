public class Constants {
    public static final String BASE_URL = "http://localhost:3000/";
    public static final String MESSAGE_SUCCESS_DELETION = "Registro excluído com sucesso";
    public static final String MESSAGE_SUCCESS_LOGIN = "Login realizado com sucesso";
    public static final String MESSAGE_FAILED_LOGIN = "Email e/ou senha inválidos";
    public static final String MESSAGE_SUCCESS_USER_CREATION = "Cadastro realizado com sucesso";
    public static final String MESSAGE_FAILED_USER_CREATION_EMAIL_ALREADY_IN_USE = "Este email já está sendo usado";
    public static final String MESSAGE_FAILED_USER_CREATION_EMPTY_EMAIL = "email não pode ficar em branco";
    public static final String MESSAGE_FAILED_USER_CREATION_EMPTY_NAME = "nome não pode ficar em branco";
    public static final String MESSAGE_FAILED_USER_CREATION_EMPTY_PASSWORD = "password não pode ficar em branco";
    public static final String MESSAGE_FAILED_USER_CREATION_WRONG_ADMIN = "administrador deve ser 'true' ou 'false'";
    public static final String MESSAGE_SUCCESS_USER_EDITION = "Registro alterado com sucesso";
    public static final String MESSAGE_SUCCESS_PRODUCT_CREATION = "Cadastro realizado com sucesso";
    public static final String MESSAGE_FAILED_PRODUCT_CREATION_NAME_ALREADY_IN_USE = "Já existe produto com esse nome";
    public static final String MESSAGE_FAILED_PRODUCT_CREATION_USER_NOT_ADMIN = "Rota exclusiva para administradores";
    public static final String MESSAGE_FAILED_PRODUCT_CREATION_EMPTY_TOKEN = "Token de acesso ausente, inválido, expirado ou usuário do token não existe mais";
}
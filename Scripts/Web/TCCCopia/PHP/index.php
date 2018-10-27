
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="UTF-8">
</head>
<body>
    
    <!-- Div autoexplicativa -->
    
    <div class="menu">
        <div class="logo"></div>
<ul>          
  <li><button style="font-family: monospace;" onclick="document.getElementById('id02').style.display='block'" class="botaologin">Logar</button></li>
  <li><button style="font-family: monospace;"   onclick="document.getElementById('id01').style.display='block'" class="botaologin">Registrar</button></li>   
  
  <li><a href="#news" class="botaomenu">Sobre nós</a></li>
  <li><a href="#contact" class="botaomenu">Contato</a></li>
  <li><a href="#about" class="botaomenu">Início</a></li>
        
</ul>
         </div>
    <div class="menu2">
    </div>
    <!-- Div da imagem principal-->
    
    <div class="banner">
        <div class="textobanner">Seja seu próprio banco</div>
        <div class="subtextobanner">O Bee UR Bank veio para lhe deixar totalmente por dentro do mundo das finanças e transformá-lo em um investidor totalmente consciente de onde está investindo seu dinheiro.</div>
        <div class="bannerbolsa">
            <iframe src="https://br.investingwidgets.com/leading-stocks?theme=darkTheme&roundedCorners=true" style="border-radius: 10px;" width="100%" height="100%" frameborder="0" allowtransparency="true" marginwidth="0" marginheight="0"></iframe><div class="poweredBy" style="font-family: Arial, Helvetica, sans-serif;"></div>
</div>
    <img src="blur.jpg" width="100%" height="100%">
    </div>
    
    <!-- Formulário do Registro -->
    
    <div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Fechar">×</span>
  <form class="modal-content animate" action="cadastro.php">
    <div class="container">
      <label><b>Nome de Usuário</b></label>
      <input type="text" placeholder="Digite seu nome de usuário" name="login" id="login" required>

      <label><b>Senha</b></label>
      <input type="password" placeholder="Digite sua senha" name="senha" id="senha" required>

      <label><b>Repetir senha</b></label>
      <input type="password" placeholder="Repita sua senha" name="psw-repeat" id="psw-repeat" required>
      
      <p>Ao criar uma conta você concorda com nossos <a href="#">Termos de Uso & Privacidade</a>.</p>
        
      <div class="clearfix">
          <br>
          <br>
        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancelar</button>
        <button type="submit" class="signupbtn" name="cadastrar" id="cadastrar">Registrar</button>
      </div>
    </div>
  </form>
</div>
    
    <!-- Formulário do Login ~ o mesmo aqui vale pro Registrar -->
    <div id="id02" class="modal">
  <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Fechar">×</span> <!-- Botão de fechar ~ o Xzinho -->
  <form class="modal-content animate" action="login.php"> <!-- Formulário do Login -->
    <div class="container"> <!-- Div pro conteúdo do formulário -->
      <label><b>Email</b></label>
      <input type="text" placeholder="Digite seu nome de usuário" name="login" id="login" required> <!-- Email -->

      <label><b>Senha</b></label>
      <input type="password" placeholder="Digite sua senha" name="senha" id="senha" required> <!-- Senha -->

      
      <input type="checkbox" checked="checked"> Lembrar de mim <!-- Isso é mais pra ficar bonitinho mesmo, não faço ideia de como fazer funcionar de fato :P -->
      <p></p>

      <div class="clearfix">
        <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancelar</button> <!-- Botão pra cancelar -->
        <button type="submit" class="signupbtn" name="entrar" id="entrar">Entrar</button> <!-- Botão pra entrar -->
      </div>
    </div>
  </form>
</div>
    
    
    <div class="fespaco"></div>
     <h1>Seu dinheiro agora será controlado da melhor forma possível!</h1>
            <div class="subh">Bee UR Bank: veja o que nossa plataforma oferece.</div>
    <div class="fespaco"></div>
    <div class="f1"><img src="question.png"   height="150px" width="150px">
    <h2>Explicar</h2>
            <div class="subh2">A plataforma irá tirar suas dúvidas sobre investimentos: Onde, como e quando investir.</div>
    </div>
    
    <div class="f2"><img src="bars.png"  height="150px" width="150px">
    <h2>Conhecer</h2>
            <div class="subh2">Também iremos apresentar os principais investimentos do mercado financeiro.</div>
    </div>
    
    <div class="f3"><img src="checklist.png" height="150px" width="150px">
    <h2>Organizar</h2>
            <div class="subh2">Assim como ajudar você a montar uma tabela de objetivos para controlar seu dinheiro.</div>
    </div>
    <hr width="65%" size="1px">
    
    <div class="f4"><img src="vector-iphone-6-white2.png" height="470px" width="650px" style="margin-left:-33%;"></div>
    <div class="f5"><h1 style="margin-top:35%;">Utilize nossa plataforma em qualquer lugar.</h1>
    <div class="subh">Tenha o seu próprio banco dentro de seu bolso, baixe nosso aplicativo.</div>
        <p></p>
        <img src="google-play-badge.pt-br.png" height="50px" width="150px">  <img src="app-store-badge.png" height="50px" width="150px">
    </div>
    
     
    
    
    <div class="rodape">
        <img src="Sem%20T%C3%ADtulo-12.png" height="170px" width="300px" style="float:left;margin-left: 4%;">
    <div class="f6">
        <h3>Empresa</h3>
        <div class="subh3">
        <a href="#">Sobre a Bee UR Bank</a>
            <p></p>
        <a href="#">Políticas de Privacidade</a>
            <p></p>
        <a href="#">Termos de Uso</a>
            <p></p>
        <a href="#">Trabalhe Conosco</a>
        </div>
        
        </div>
        <div class="f7">
        <h3>Fale conosco</h3>
        <div class="subh3">
            <a href="#">(011) 98274-4354</a>
            <p></p>
            <a href="#">(011) 4231-9519</a>
            <p></p>
            <a href="#">contato@beeurbank.com.br</a>
            <p></p>
            <a href="#">sugestoes@beeurbank.com.br</a>
            </div>
        </div>
        <div class="f8">
        <h3>Redes Sociais</h3>
        <div class="subh4"><img src="facebook-app-logo.png" width="30px" height="30px" style="margin-right: 3%;">
            <img src="instagram.png" width="30px" height="30px" style="margin-right: 2%;">
            <img src="twitter-logo-on-black-background.png" width="30px" height="30px" style="margin-right: 3%;">
            <img src="snapchat.png" width="30px" height="30px" style="margin-right: 3%;">
            </div>
        </div>
        
    </div>
   
    
<!-- Script pra fechar o troço quando clicar fora da tela -->
<script>
// Get the modal
var modal = document.getElementById('id01');
var modal2 = document.getElementById('id02');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

window.onclick = function(event) {
    if (event.target == modal2) {
        modal2.style.display = "none";
    }
}
</script>
      
    </body>

</html>
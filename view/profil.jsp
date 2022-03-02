<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Profil</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/4-col-portfolio.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/ca09bd6d26.js" crossorigin="anonymous"></script>
</head>
<body>
	<% String lienPageAccueil = request.getContextPath();%>
	<%@ include file="entete.jspf" %>
	<h2 class="profil">Mon Profil</h2>
	<form>
	  <div class="form-group row d-flex justify-content-center justify-content-md-center">
	    <label for="inlineFormInputName" class="col-sm-2 col-form-label col-md-auto ">Pseudo :</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="inlineFormInputName">
	    </div>
	    <label for="inputPassword3" class="col-2 col-form-label col-md-auto">Nom :</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control">
	    </div>
	  </div>
	   <div class="form-group row d-flex justify-content-center">
	    <label for="inlineFormInputName" class="col-sm-2 col-form-label col-md-auto">Prénom :</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="inlineFormInputName">
	    </div>
	    <label for="inputEmail3" class="col-sm-2 col-form-label col-md-auto">Email :</label>
	    <div class="col-sm-3">
	      <input type="email" class="form-control" id="inputEmail3">
	    </div>
	  </div>
	   <div class="form-group row d-flex justify-content-center">
	    <label for="phone" class="col-sm-2 col-form-label col-md-auto">Téléphone :</label>
	    <div class="col-sm-3">
	      <input type="tel" class="form-control">
	    </div>
	    <label for="inputEmail3" class="col-sm-2 col-form-label col-md-auto">Rue :</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control">
	    </div>
	  </div>
	  <div class="form-group row d-flex justify-content-center">
	    <label for="inputZip" class="col-sm-2 col-form-label col-md-auto pr-0">Code postal :</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control pl-0" id="inputZip">
	    </div>
	    <label for="inputAddress" class="col-sm-2 col-form-label col-md-auto">Ville :</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control"id="inputAddress">
	    </div>
	  </div>
	  <div class="form-group row d-flex justify-content-center">
	    <label for="exampleInputPassword1" class="col-sm-2 pass1 col-form-label">Mot de passe actuel :</label>
	    <div class="col-sm-3">
	      <input type="password"class="form-control" id="exampleInputPassword1">
	    </div>
	    <label for="inputAddress" class="col-sm-2 vh col-form-label col-md-auto">Rue :</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control vh"id="inputAddress">
	    </div>
	   </div>
	   <div class="form-group row d-flex justify-content-center">
	    <label for="exampleInputPassword1" class="col-sm-1 pass1 col-form-label">Nouveau Mot de passe :</label>
	    <div class="col-sm-3">
	      <input type="password"class="form-control" id="exampleInputPassword1">
	    </div>
	     <label for="exampleInputPassword1" class="col-sm-2 pass1 col-form-label col-md-auto">Confirmer :</label>
	    <div class="col-sm-3">
	      <input type="password"class="form-control" id="exampleInputPassword1">
	    </div>
	    </div>
	   <div class="form-group row">
	  	<button type="submit" class="btn btn-primary">Modifier</button>
	  	<button type="submit" class="btn btn-primary">Supprimer mon compte</button>
	  </div>
	</form>
</body>
</html>
$mysqlDataDllPath = "C:\Program Files (x86)\MySQL\MySQL Connector NET 8.2.0\MySql.Data.dll"

Add-Type -Path $mysqlDataDllPath

$server = "localhost"
$database = "gestiontempsdetravail"
$username = "root"
$password = ""

# Créer une chaîne de connexion
$connectionString = "Server=$server;Database=$database;Uid=$username;Pwd=$password;"

# Créer une connexion MySQL
$connection = New-Object MySql.Data.MySqlClient.MySqlConnection
$connection.ConnectionString = $connectionString

# Ouvrir la connexion
$connection.Open()

if ($connection.State -eq 'Open') {
    # La connexion à la base de données a réussi
    $query = "SELECT * FROM employee"

    # Créer une commande MySQL
    $command = $connection.CreateCommand()
    $command.CommandText = $query

    # Exécuter la requête et récupérer les résultats
    $adapter = New-Object MySql.Data.MySqlClient.MySqlDataAdapter $command
    $dataset = New-Object System.Data.DataSet
    $adapter.Fill($dataset) | Out-Null
    $result = $dataset.Tables[0]
    
    # Parcourir la liste des employés
    foreach ($row in $result.Rows) {

        # Charger la bibliothèque iTextSharp
        Add-Type -Path "C:\Scripts\itextsharp.dll"  # Assurez-vous de spécifier le chemin correct

        # Créer un nouveau document PDF
        $document = New-Object iTextSharp.text.Document

        # Obtenir la date actuelle au format AAAAMMJJ (AnnéeMoisJour)
        $dateAujourdhui = (Get-Date).ToString("yyyyMMdd")


        # Spécifier le chemin de sortie du fichier PDF
        $cheminPdf = "C:\Users\dell 7470\Desktop\TP Gestion du temps de travail\front_gestionDeTempsDeTravail\src\assets\rapports\"+$row["id_employee"]+"_"+$dateAujourdhui+".pdf"

        # Créer un écrivain PDF pour écrire dans le fichier PDF
        $écrivain = [iTextSharp.text.pdf.PdfWriter]::GetInstance($document, [System.IO.File]::Create($cheminPdf))

        # Ouvrir le document pour écrire
        $document.Open()
            $imagePath = "C:\Users\dell 7470\Desktop\logo.png"
    if (Test-Path $imagePath) {
        $image = [iTextSharp.text.Image]::GetInstance($imagePath)
        $image.ScaleToFit(100, 100) 
        $document.Add($image)
    }


    $paragrapheEmployeeNumber = New-Object iTextSharp.text.Paragraph
$paragrapheEmployeeNumber.Add("Numéro d'employé : " + $row["id_employee"])
$paragrapheEmployeeNumber.SpacingBefore = 20
$paragrapheEmployeeNumber.IndentationLeft = 60 
$document.Add($paragrapheEmployeeNumber)
      
$paragrapheNom = New-Object iTextSharp.text.Paragraph
$paragrapheNom.Add("Nom: " + $row["nom"])
$paragrapheNom.SpacingBefore = 10
$paragrapheNom.IndentationLeft = 60 
$document.Add($paragrapheNom)

$paragraphePrenom = New-Object iTextSharp.text.Paragraph
$paragraphePrenom.Add("Prenom: " + $row["prenom"])
$paragraphePrenom.SpacingBefore = 10
$paragraphePrenom.IndentationLeft = 60 
$document.Add($paragraphePrenom)

$paragrapheEmail = New-Object iTextSharp.text.Paragraph
$paragrapheEmail.Add("Email: " + $row["email"])
$paragrapheEmail.SpacingBefore = 10
$paragrapheEmail.IndentationLeft = 60 
$document.Add($paragrapheEmail)



# Créer un tableau
$table = New-Object iTextSharp.text.pdf.PdfPTable(6) 
$table.SpacingBefore = 30
$table.SpacingAfter = 30
$columnWidths = [float[]]@(25, 15, 15, 15, 15, 15)

$table.SetWidths($columnWidths)

# Ajouter des entêtes de colonnes
$cellDateHeader = New-Object iTextSharp.text.pdf.PdfPCell([iTextSharp.text.Phrase]::New("Date"))
$cellDateHeader.BackgroundColor = [iTextSharp.text.BaseColor]::CYAN
$table.AddCell($cellDateHeader)

$cellHeuresDebut = New-Object iTextSharp.text.pdf.PdfPCell([iTextSharp.text.Phrase]::New("Debut"))
$cellHeuresDebut.BackgroundColor = [iTextSharp.text.BaseColor]::CYAN
$table.AddCell($cellHeuresDebut)

$cellHeuresFin = New-Object iTextSharp.text.pdf.PdfPCell([iTextSharp.text.Phrase]::New("Fin"))
$cellHeuresFin.BackgroundColor = [iTextSharp.text.BaseColor]::CYAN
$table.AddCell($cellHeuresFin)

$cellHeuresTravailleesHeader = New-Object iTextSharp.text.pdf.PdfPCell([iTextSharp.text.Phrase]::New("Heures travaillées"))
$cellHeuresTravailleesHeader.BackgroundColor = [iTextSharp.text.BaseColor]::CYAN
$table.AddCell($cellHeuresTravailleesHeader)

$cellHeuresSupplementairesHeader = New-Object iTextSharp.text.pdf.PdfPCell([iTextSharp.text.Phrase]::New("Heures supp."))
$cellHeuresSupplementairesHeader.BackgroundColor = [iTextSharp.text.BaseColor]::CYAN
$table.AddCell($cellHeuresSupplementairesHeader)

$cellHeuresDePausesHeader = New-Object iTextSharp.text.pdf.PdfPCell([iTextSharp.text.Phrase]::New("Heures de pauses"))
$cellHeuresDePausesHeader.BackgroundColor = [iTextSharp.text.BaseColor]::CYAN
$table.AddCell($cellHeuresDePausesHeader)



$query2 = "SELECT t.*, TIMEDIFF(heure_fin, heure_debut) AS duree_travail, (SELECT COUNT(*) FROM pauses p WHERE p.id_temps = t.id_temps) AS nb_pauses, (SELECT TIME_FORMAT(SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(p.fin_pause, p.debut_pause)))), '%H:%i:%s') FROM pauses p WHERE p.id_temps = t.id_temps) AS duree_totale_pauses FROM temps t WHERE date >= DATE_SUB("+$dateAujourdhui+", INTERVAL 6 DAY) AND date < "+$dateAujourdhui+" AND id_employee = " + $row["id_employee"]

$command2 = $connection.CreateCommand()
$command2.CommandText = $query2
$dataReader = $command2.ExecuteReader()
while ($dataReader.Read()) {
    
    $date = [DateTime]::Parse($dataReader["date"])  
    $frCulture = [System.Globalization.CultureInfo]::GetCultureInfo("fr-FR")
$dateFormatted = $date.ToString("ddd dd MMM yyyy", $frCulture)


    $debutTravail=$dataReader["heure_debut"]
    $finTravail=$dataReader["heure_fin"]
    $nbPauses = $dataReader["nb_pauses"]
    $dureeTotalePauses = $dataReader["duree_totale_pauses"]
    $dureeTravailString = $dataReader["duree_travail"]
    $dureeTravail = [TimeSpan]::ParseExact($dureeTravailString, "hh\:mm\:ss", $null)

    # Calcul des heures supplémentaires
    $heuresSupplementaires = $dureeTravail.TotalHours - 8
    $cellDateValue = New-Object iTextSharp.text.pdf.PdfPCell([iTextSharp.text.Phrase]::New($dateFormatted))
    $cellHeureDebutValue = New-Object iTextSharp.text.pdf.PdfPCell([iTextSharp.text.Phrase]::New($debutTravail))
    $cellHeureFinValue = New-Object iTextSharp.text.pdf.PdfPCell([iTextSharp.text.Phrase]::New($finTravail))
    $cellHeuresTravailleesValue = New-Object iTextSharp.text.pdf.PdfPCell([iTextSharp.text.Phrase]::New($dureeTravailString))
    $cellHeuresSupplementairesValue = New-Object iTextSharp.text.pdf.PdfPCell([iTextSharp.text.Phrase]::New($heuresSupplementaires))
    $cellHeuresDePausesValue = New-Object iTextSharp.text.pdf.PdfPCell([iTextSharp.text.Phrase]::New($nbPauses))
    
    $table.AddCell($cellDateValue)
    $table.AddCell($cellHeureDebutValue)
    $table.AddCell($cellHeureFinValue)
    $table.AddCell($cellHeuresTravailleesValue)
    $table.AddCell($cellHeuresSupplementairesValue)
    $table.AddCell($cellHeuresDePausesValue)
}


$dataReader.Close()

$table.AddCell($cellDateValue)
$table.AddCell($cellHeuresTravailleesValue)
$table.AddCell($cellHeuresSupplementairesValue)
$table.AddCell($cellHeuresDePausesValue)

# Répéter pour chaque ligne de données supplémentaire que vous souhaitez ajouter

# ...

# Ajouter le tableau au document
$document.Add($table)





        # Fermer le document PDF
        $document.Close()

        # Afficher un message de confirmation
    Write-Host "Le fichier PDF a été généré avec succès à $cheminPdf."

# Vérifier si un enregistrement avec le même jour existe
$selectExistingQuery = "SELECT COUNT(*) FROM rapport WHERE id_employee = @id_employee AND DATE(date) = DATE(NOW())"

$selectExistingCommand = $connection.CreateCommand()
$selectExistingCommand.CommandText = $selectExistingQuery

$selectExistingCommand.Parameters.AddWithValue("@id_employee", $row["id_employee"])

$existingCount = $selectExistingCommand.ExecuteScalar()

if ($existingCount -gt 0) {
    Write-Host "Un enregistrement avec le même jour existe déjà pour cet employé."
} else {
    # Insérer le chemin du rapport dans la base de données
    $insertQuery = "INSERT INTO rapport (date , path , id_employee ) VALUES (NOW(), @chemin_rapport, @id_employee)"

    $insertCommand = $connection.CreateCommand()
    $insertCommand.CommandText = $insertQuery

    $insertCommand.Parameters.AddWithValue("@chemin_rapport", "\assets\rapports\"+$row["id_employee"]+"_"+$dateAujourdhui+".pdf")
    $insertCommand.Parameters.AddWithValue("@id_employee", $row["id_employee"])

    $insertCommand.ExecuteNonQuery()

    Write-Host "Chemin du rapport inséré dans la base de données."
}



    }

    # Fermer la connexion
    $connection.Close()
} else {
    Write-Host "La connexion à la base de données a échoué."
}

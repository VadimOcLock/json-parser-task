<#-- @ftlvariable name="rootItems" type="kotlin.collections.List<com.example.models.Item>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>task</title>
</head>
<body style="text-align: center; font-family: sans-serif">
<h1>Root items list </h1>
<hr>
<#list rootItems as item>
    <div>
        <h3>
            ${item.id}
        </h3>
        <p>
            ${item.description}
        </p>
    </div>
</#list>
<hr>
</body>
</html>
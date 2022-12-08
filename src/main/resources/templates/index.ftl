<!DOCTYPE html>
<html lang="en">
<head>
    <title>task</title>
    <style>
        html { margin:0; padding:0; font-size:62.5%; }
        body { max-width:800px; min-width:300px; margin:0 auto; padding:20px 10px; font-size:14px; font-size:1.4em; }
        h1 { font-size:1.8em; }
        .demo { overflow:auto; border:1px solid silver; min-height:100px; }
    </style>
</head>
<body>
    <h1> ИЕРАРХИЯ ТЕХНИЧЕСКИХ МЕСТ </h1>
    <div id="tree" class="demo"></div>

    <label for="uname">ID: </label>
    <input id="id" type="text" readonly="true"></input>
    <label for="uname">Parent ID: </label>
    <input id="parentId" type="text" readonly="true"></input>
    <label for="uname">Название: </label>
    <input id="name" type="text" readonly="true"></input>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
    <script>
        $('#tree')
        .on("changed.jstree", function (e, data) {
            if (data.selected.length) {
                $('#id').val(data.instance.get_node(data.selected[0]).id)
                $('#parentId').val(data.instance.get_node(data.selected[0]).parent)
                $('#name').val(data.instance.get_node(data.selected[0]).text)
            }
        })
        .jstree({
            'core' : {
                'data' : {
                    'url' : '/v1/tree',
                    'data' : function (node) {
                        return { 'id' : node.id };
                    }
                }
            }
        })
    </script>
</body>
</html>
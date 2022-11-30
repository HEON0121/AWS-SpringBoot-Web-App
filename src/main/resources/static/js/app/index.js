var main = {
    //var recognition = new SpeechRecognition();
             //const grammar = '#JSGF V1.0; grammar colors; public <color> = aqua | azure | beige | bisque | black | blue | brown | chocolate | coral | crimson | cyan | fuchsia | ghostwhite | gold | goldenrod | gray | green | indigo | ivory | khaki | lavender | lime | linen | magenta | maroon | moccasin | navy | olive | orange | orchid | peru | pink | plum | purple | red | salmon | sienna | silver | snow | tan | teal | thistle | tomato | turquoise | violet | white | yellow ;'

    init : function() {
        var _this = this;

        $('#btn-save').on('click', function(){
            _this.save();
        });

        $('#btn-update').on('click', function(){
            _this.update();
        });

        $('#btn-delete').on('click', function(){
            _this.delete();
        });

        $('#btn-stt').on('click', function(){
            _this.speechToText();
        })
    },
    save : function() {
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (e) {
            alert(JSON.stringify(e));
        });
    },

    update : function() {
        var data = {
            title : $('#title').val(),
            content : $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type : 'PUT',
            url : '/api/v1/posts/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function(e) {
            alert(JSON.stringify(e));
        });
    },

    delete : function() {
        var id = $('#id').val();

        $.ajax({
            type : 'DELETE',
            url : '/api/v1/posts/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function(e) {
            alert(JSON.stringify(e));
        });
    },

    speechToText : function() {
        $('#content').val('');
        alert('Ready to return your speech into text.');
        $(document).ready(function() {
                $('#content').focus();
        });

        var SpeechRecognition = SpeechRecognition || webkitSpeechRecognition;
        var recognition = new SpeechRecognition();

//                //const speechRecognitionList = new SpeechGrammarList();
//                //speechRecognitionList.addFromString(grammar, 1);
//                //recognition.grammars = speechRecognitionList;
        recognition.continuous = false;
        recognition.lang = 'en-US';
        recognition.interimResults = false;
        recognition.maxAlternatives = 1;
        recognition.start();


        recognition.onresult = (event) => {
            var transcript = event.results[0][0].transcript;
            //diagnostic.textContent = `Result received: ${color}`;
            //bg.style.backgroundColor = color;
            alert(transcript);
            $('#content').val(transcript);
        }

        recognition.onerror = function(event) {
            alert("오류 발생");
        }


        // 말하기
        // 텍스트로 변환







         //alert('인식 시작');
         //const diagnostic = document.querySelector('.output');
         //const bg = document.querySelector('html');




    }
};

main.init();
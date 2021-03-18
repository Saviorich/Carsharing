function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('.editor_block__image img')
                .attr('src', e.target.result)
        };

        reader.readAsDataURL(input.files[0]);
    }
}

$(document).ready(function(){
    $("#view").click(function(){

        alert($('#brand_editor').val() + " " + $('#model_editor').val())
        $('.car_block__brand span').text($('#brand_editor').val() + " " + $('#model_editor').val());
        $('.car_block__img img').attr('src', $('.editor_block__image img').attr('src'))

        var data = CKEDITOR.instances['content_editor'].getData();
        $('.news_block__header span').text($("#header_editor").val());
        $('.news_block__content span').html(data);
        $('.news_block__img img').attr('src', $('.editor_block__image img').attr('src'))
    });
});
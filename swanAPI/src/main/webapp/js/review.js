/**
 * 
 */
$(document).ready(function() {

    $('#review').on('keyup', function() {

        if($(this).val().length > 140) {
            $(this).val($(this).val().substring(0, 140));
            alert("140자 이내로 작성해 주세요.")
        }

    });

});
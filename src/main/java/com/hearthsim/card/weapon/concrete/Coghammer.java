package com.hearthsim.card.weapon.concrete;

import com.hearthsim.card.Card;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.weapon.WeaponCard;
import com.hearthsim.event.CharacterFilter;
import com.hearthsim.event.effect.CardEffectCharacter;
import com.hearthsim.event.effect.CardEffectOnResolveRandomCharacterInterface;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.HearthTreeNode;

public class Coghammer extends WeaponCard implements CardEffectOnResolveRandomCharacterInterface {
    private static final CardEffectCharacter effect = (originSide, origin, targetSide, targetCharacterIndex, boardState) -> {
        Minion targetCharacter = boardState.data_.modelForSide(targetSide).getCharacter(targetCharacterIndex);
        targetCharacter.setDivineShield(true);
        targetCharacter.setTaunt(true);
        return boardState;
    };

    @Override
    public CardEffectCharacter getRandomTargetEffect() {
        return Coghammer.effect;
    }

    @Override
    public CharacterFilter getRandomTargetFilter() {
        return CharacterFilter.FRIENDLY_MINIONS;
    }
}

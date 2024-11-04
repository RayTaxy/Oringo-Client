package oringo.mixin;

import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({C01PacketEncryptionResponse.class})
public interface C01PacketEncryptionResponseAccessor {
   @Accessor("secretKeyEncrypted")
   void setSecretKeyEncrypted(byte[] var1);
}
